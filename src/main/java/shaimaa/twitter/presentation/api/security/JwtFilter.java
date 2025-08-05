//package shaimaa.twitter.presentation.api.security;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.MalformedJwtException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Component
//class JwtFilter extends OncePerRequestFilter {
//    @Autowired
//    private JwtUtil jwtUtil;
//
////    JwtFilter(AuthenticationManager authenticationManager) {
////        super(authenticationManager);
////    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        String header = request.getHeader("Authorization");
//        if (header == null || !header.startsWith("Bearer ")) {
//            System.out.println("here");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
////            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//        UsernamePasswordAuthenticationToken authentication = getAuthentication(header.replace("Bearer", ""));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        filterChain.doFilter(request, response);
//        } catch (MalformedJwtException | ExpiredJwtException e) {
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            return;
//        }
//    }
//
//    private UsernamePasswordAuthenticationToken getAuthentication(String token) {
//            String userId = jwtUtil.getSubject(token);
//
//            return new UsernamePasswordAuthenticationToken(userId, null, null);
//    }
//}
