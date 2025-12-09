package com.learnspring.service;


import com.learnspring.dto.request.AuthenticationRequest;
import com.learnspring.exception.AppException;
import com.learnspring.exception.ErrorCode;
import com.learnspring.model.User;
import com.learnspring.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    public final boolean authenticate(AuthenticationRequest request){
        var user = userRepository.findByUsername(request.getUsername()).
                orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return true ;
    }
}

