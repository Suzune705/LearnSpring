## @RequestBody

- What does it do ?
    - Receive data from JSON / XML / body text which is sent from client
    - Automatically deserialize data into object
    - Most used for Post and Put method

- Work Flow :
    - Spring read JSON from HTTP body  
      → Use JackSon to convert it into Java Object  
      → Throws it to variable that has `@RequestBody`

---
## @ModelAtribute 
    - 

---

## @RequestParam

- What does it do ?
    - Receive data from Query Param and Form Submit (`?name=Hello`)
    - This is same like :
      ```
      request.getParameter("name")
      ```

---

## @PathVariable

- Receive data in URL path
    - Example:
      ```
      GET /user/10
      ```

---

## Summarize

- `/user/10` → `@PathVariable`
- `/user?id=10` → `@RequestParam`
- `{ "name": "Hello" }` → `@RequestBody`

## @RestController 
    - To mark class is REST Controller
    - Automatically response with JSON or XML
    - Only return data 
    
## @RequestMapping 
    - Identify URL , HTTP method 
    - It is used for class and method level  (class - level path ) + (method-level path ) 
    - use for class level to prefix url path 

## @RequiredArgsConstructor 
    - To generate constructor for final or @NonNull field 

## @Controller
    - Return HTML view 













