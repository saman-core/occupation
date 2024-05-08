package io.samancore.occupation.application.service.impl;

import io.samancore.occupation.application.service.OccupationService;
import io.samancore.occupation.application.transformer.OccupationTransformer;
import io.samancore.occupation.data.repository.OccupationRepository;
import io.samancore.occupation.data.utils.page.PageRequest;
import io.samancore.occupation.data.utils.page.PageUtil;
import io.samancore.occupation.model.Occupation;
import io.samancore.occupation.model.PageData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

@ApplicationScoped
public class OccupationServiceImpl implements OccupationService {

    @Inject
    Logger log;

    @Inject
    OccupationRepository repository;

    @Inject
    OccupationTransformer transformer;

    @Override
    public PageData<Occupation> getPageByLabel(String label, PageRequest pageRequest) {
        log.debugf("OccupationServiceImpl.getPageByLabel %s", label);
        var occupations = repository.getPageByLabel(label, pageRequest);
        return PageUtil.toPageModel(occupations, transformer::toModel);
    }
}
