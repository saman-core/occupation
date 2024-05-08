package io.samancore.occupation.data.repository.panache;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.samancore.occupation.data.entity.OccupationEntity;
import io.samancore.occupation.data.repository.OccupationRepository;
import io.samancore.occupation.data.repository.constants.RepositoryConstants;
import io.samancore.occupation.data.utils.page.PagePanacheUtil;
import io.samancore.occupation.data.utils.page.PageRequest;
import io.samancore.occupation.model.PageData;
import io.samancore.occupation.model.type.GeneralStatus;

import org.jboss.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class OccupationRepositoryPanache implements PanacheRepository<OccupationEntity>, OccupationRepository {

    @Inject
    Logger log;

    @Override
    public List<OccupationEntity> getAll() {
        log.debug("OccupationRepositoryPanache.getAll");
            return list(
                RepositoryConstants.FILTER_GENERAL_STATUS, 
                Map.of(RepositoryConstants.KEY_GENERAL_STATUS, GeneralStatus.ACTIVE));
    }

    @Override
    public List<OccupationEntity> searchByLabel(String label) {
        log.debugf("OccupationRepositoryPanache.searchByLabel %s ", label);
        if(label != null) {
            String search = "%" + label.toUpperCase() + "%";
            return list(RepositoryConstants.LIKE_LABEL, 
                search);
        }else return list(
            RepositoryConstants.FILTER_GENERAL_STATUS, 
            Map.of(RepositoryConstants.KEY_GENERAL_STATUS, GeneralStatus.ACTIVE));
        
    }

    @Override
    public PageData<OccupationEntity> getPageByLabel(String label, PageRequest pageRequest) {
        log.debugf("OccupationRepositoryPanache.getPageByLabel %s", label);
        String search = "%" + label.toUpperCase() + "%";
        var params = new HashMap<String, Object>();
            params.put("name", search);
            params.put("status", GeneralStatus.ACTIVE);
        var result = RepositoryConstants.LIKE_LABEL+RepositoryConstants.AND+RepositoryConstants.FILTER_GENERAL_STATUS;
        var query = this.find(result, PagePanacheUtil.generateSort(pageRequest), params);
        var list = query.page(PagePanacheUtil.generatePage(pageRequest)).list();
        var total = query.count();
        return PageData.<OccupationEntity>newBuilder().setData(list).setCount(total).build();
    }

    @Override
    public PageData<OccupationEntity> getPage(PageRequest pageRequest) {
        log.debugf("OccupationRepositoryPanache.getPage");
        var query = this.find(RepositoryConstants.FILTER_GENERAL_STATUS, PagePanacheUtil.generateSort(pageRequest), Map.of(RepositoryConstants.KEY_GENERAL_STATUS, GeneralStatus.ACTIVE));
        var list = query.page(PagePanacheUtil.generatePage(pageRequest)).list();
        var total = query.count();
        return PageData.<OccupationEntity>newBuilder().setData(list).setCount(total).build();
    }
}