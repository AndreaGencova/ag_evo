package com.ag_evo.service;

import com.ag_evo.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.collections4.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PolicyServiceImpl implements PolicyService {
    private static final YoungerThan youngerThan = YoungerThan.getInstance();
    private static final EmailDomainIs emailDomainIs = EmailDomainIs.getInstance();
    private static final IsMemberOf isMemberOf = IsMemberOf.getInstance();


    public List<String> resolvePolicies(Users user){
        List<String> result = new ArrayList<>();
        CollectionUtils.addIgnoreNull(result, youngerThan.recalculate(user));
        CollectionUtils.addIgnoreNull(result, emailDomainIs.recalculate(user));
        CollectionUtils.addIgnoreNull(result, isMemberOf.recalculate(user));
        return result;
    }
}
