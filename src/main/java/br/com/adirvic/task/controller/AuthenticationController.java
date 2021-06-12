package br.com.adirvic.task.controller;

import br.com.adirvic.task.model.Login;
import br.com.adirvic.task.model.AuthenticationResponse;
import br.com.adirvic.task.util.JWTUtil;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final @NonNull
    AuthenticationManager authenticationManager;

    @Qualifier("userDetailsServiceImpl")
    private final @NonNull
    UserDetailsService userDetailsService;

    private final @NonNull
    JWTUtil jwtTokenUtil;

    @RequestMapping(value = "/v1/login", method = {RequestMethod.POST})
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody Login login) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword())
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());

            String authToken = jwtTokenUtil.generateToken(userDetails);
            AuthenticationResponse response = new AuthenticationResponse(true, "", authToken);

            return ResponseEntity.ok(response);

        } catch (BadCredentialsException ex) {

            AuthenticationResponse response = new AuthenticationResponse();
            response.setSuccess(false);
            response.setErrormsg("Usúario ou senha inválido");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception ex) {

            AuthenticationResponse response = new AuthenticationResponse();
            response.setSuccess(false);
            response.setErrormsg("Falha interna do servidor");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}
