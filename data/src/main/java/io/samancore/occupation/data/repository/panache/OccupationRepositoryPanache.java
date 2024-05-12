package io.samancore.occupation.data.repository.panache;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.samancore.occupation.data.entity.OccupationEntity;
import io.samancore.occupation.data.repository.OccupationRepository;
import io.samancore.occupation.data.repository.constants.RepositoryConstants;
import io.samancore.occupation.data.utils.page.PagePanacheUtil;
import io.samancore.occupation.data.utils.page.PageRequest;
import io.samancore.occupation.model.PageData;
import io.samancore.occupation.model.type.GeneralStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class OccupationRepositoryPanache implements PanacheRepository<OccupationEntity>, OccupationRepository {

    @Inject
    Logger log;

    @Override
    public PageData<OccupationEntity> getPageByParams(List<Long> ids, String label, PageRequest pageRequest) {
        log.debugf("OccupationRepositoryPanache.getPageByLabel %s", label);

        String search = "%" + label + "%";
        var params = new HashMap<String, Object>();
        params.put("name", search);
        params.put("status", GeneralStatus.ACTIVE);

        PanacheQuery<OccupationEntity> query;
        if (ids != null && !ids.isEmpty()) {
            query = this.find("id in (?1)", ids);
        } else if (label != null) {
            var result = RepositoryConstants.LIKE_LABEL + RepositoryConstants.AND + RepositoryConstants.FILTER_GENERAL_STATUS;
            query = this.find(result, PagePanacheUtil.generateSort(pageRequest), params);
        } else {
            query = this.find(RepositoryConstants.FILTER_GENERAL_STATUS, PagePanacheUtil.generateSort(pageRequest), Map.of(RepositoryConstants.KEY_GENERAL_STATUS, GeneralStatus.ACTIVE));
        }

        var list = query.page(PagePanacheUtil.generatePage(pageRequest)).list();
        var total = query.count();
        return PageData.<OccupationEntity>newBuilder().setData(list).setCount(total).build();
    }

    @Override
    public OccupationEntity getById(Long id) {
        log.debugf("OccupationRepositoryPanache.getById %s", id);
        return this.findById(id);
    }
}