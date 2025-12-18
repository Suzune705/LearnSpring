package com.learnspring.service;


import com.learnspring.dto.request.AuthenticationRequest;
import com.learnspring.dto.request.IntrospectRequest;
import com.learnspring.dto.response.AuthenticationResponse;
import com.learnspring.dto.response.IntrospectResponse;
import com.learnspring.exception.AppException;
import com.learnspring.enums.ErrorCodeType;
import com.learnspring.model.User;
import com.learnspring.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    @Value("${jwt.signerKey}")
    private String SIGNER_KEY ;

    public final AuthenticationResponse authenticate(AuthenticationRequest request){
        User user = userRepository.findByUsername(request.getUsername()).
                orElseThrow(() -> new AppException(ErrorCodeType.USER_NOT_EXISTED));
        if(!user.getPassword().equals(request.getPassword())){
           throw new AppException(ErrorCodeType.USER_PASSWORD_INVALID);
        }
        String token = generateToken(user);
        return  AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }
    private String generateToken(User user){
        JWSHeader jwtHeader = new JWSHeader(JWSAlgorithm.HS512); // set type of algo
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername()) // name or email login account
                .issuer("learnSpring.com") // domain
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(user))  // name : user of the token , value can include : userID,role, permissions, ...
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject()); // transfer Claim to PayLoad of JWT

        //  create jwt object that include header and payload before signing because JWT = header + payload + signature;
        // signature is calculated from the header + payload
        JWSObject jwsObject = new JWSObject(jwtHeader,payload);
        try{
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes(StandardCharsets.UTF_8))); // convert key to [] byte
            return jwsObject.serialize(); // JWS Object -> JWT : header.payload.signature
        }catch (JOSEException e){
            log.error("Cannot create token", e);
            throw new RuntimeException(e);
        }
    }
    public IntrospectResponse introspect(IntrospectRequest request) {
        String token = request.getToken();
        try{
            JWSVerifier jwsVerifier = new MACVerifier(SIGNER_KEY.getBytes(StandardCharsets.UTF_8));
            SignedJWT signedJWT = SignedJWT.parse(token);
           // expiryDate.getTime() > now.getTime()
            boolean expiryDate  = signedJWT.getJWTClaimsSet().getExpirationTime().after(new Date()); // new Date : current time ,
           boolean verified = signedJWT.verify(jwsVerifier);
           return IntrospectResponse.builder()
                   .valid(verified && expiryDate)
                   .build();
        }catch (JOSEException e){
                throw new AppException(ErrorCodeType.INVALID_TOKEN);
        }catch (ParseException e){
               throw  new AppException(ErrorCodeType.INVALID_SIGNATURE);
        }
    }
    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");
//        if(!CollectionUtils.isEmpty(user.getRoles())){
//            user.getRoles().forEach(stringJoiner::add);
//        }
        return stringJoiner.toString();
    }
}

