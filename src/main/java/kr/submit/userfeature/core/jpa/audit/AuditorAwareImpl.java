package kr.submit.userfeature.core.jpa.audit;

import kr.submit.userfeature.core.security.dto.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@EnableJpaAuditing
@Component
public class AuditorAwareImpl implements AuditorAware<Long> {

    private static final String ANONYMOUS_USER = "anonymousUser";

    private static boolean notEqualsAnonymousUser(String value) {
        return !ANONYMOUS_USER.equalsIgnoreCase(value);
    }

    @Override
    public Optional<Long> getCurrentAuditor() {

        return Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getName)
                .filter(AuditorAwareImpl::notEqualsAnonymousUser)
                .map(Long::valueOf);
    }
}