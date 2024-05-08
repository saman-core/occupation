package io.samancore.occupation.application.service.impl;

import org.jboss.logging.Logger;

import io.samancore.occupation.application.service.OccupationService;
import io.samancore.occupation.application.transformer.OccupationTransformer;
import io.samancore.occupation.data.repository.OccupationRepository;
import io.samancore.occupation.data.utils.page.PageRequest;
import io.samancore.occupation.data.utils.page.PageUtil;
import io.samancore.occupation.model.Occupation;
import io.samancore.occupation.model.PageData;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class OccupationServiceImpl implements OccupationService {

    @Inject
    Logger log;

    @Inject
    OccupationRepository repository;

    @Inject
    OccupationTransformer transformer;

    @Override
    public List<Occupation> getAll() {
        log.debug("OccupationServiceImpl.getAll");
        return repository.getAll()
                .stream()
                .map(transformer::toModel).toList();
    }

    @Override
    public List<Occupation> searchByLabel(String label) {
        log.debugf("OccupationServiceImpl.searchByLabel %s", label);
        return repository.searchByLabel(label)
                .stream()
                .map(transformer::toModel).toList();
    }

    @Override
    public PageData<Occupation> getPageByLabel(String label, PageRequest pageRequest) {
        log.debugf("OccupationServiceImpl.getPageByLabel %s", label);
        var occupations = repository.getPageByLabel(label, pageRequest);
        return PageUtil.toPageModel(occupations, transformer::toModel);
    }

    @Override
    public PageData<Occupation> getPage(PageRequest pageRequest) {
        log.debugf("OccupationServiceImpl.getPage");
        var occupations = repository.getPage(pageRequest);
        return PageUtil.toPageModel(occupations, transformer::toModel);
    }
}
