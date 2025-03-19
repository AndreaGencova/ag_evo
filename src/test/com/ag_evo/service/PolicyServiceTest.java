package com.ag_evo.service;

import com.ag_evo.model.*;
import com.ag_evo.repository.UsersRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PolicyServiceImpl.class})
public class PolicyServiceTest {

    @InjectMocks
    private PolicyServiceImpl policyService;

    /*
    @Test
    public void resolvePoliciesYoungerThan_OK(){
        final YoungerThan youngerThan = mock(YoungerThan.class);
        final EmailDomainIs emailDomainIs = mock(EmailDomainIs.class);
        final IsMemberOf isMemberOf = mock(IsMemberOf.class);

        when(youngerThan.recalculate(any(Users.class))).thenReturn("yes");
        when(emailDomainIs.recalculate(any(Users.class))).thenReturn("yes");
        when(isMemberOf.recalculate(any(Users.class))).thenReturn("yes");

        List<String> result = policyService.resolvePolicies(any(Users.class));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.size());
    }*/
}
