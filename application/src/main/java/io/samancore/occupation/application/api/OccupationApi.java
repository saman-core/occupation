package io.samancore.occupation.application.api;

import io.samancore.occupation.application.service.OccupationService;
import io.samancore.occupation.data.utils.page.PageRequest;
import io.samancore.occupation.data.utils.page.PageUtil;
import io.samancore.occupation.model.Occupation;
import io.samancore.occupation.model.PageData;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestQuery;
import org.jboss.resteasy.reactive.Separator;

import java.util.List;

@Path("/occupation")
public class OccupationApi {

    @Inject
    Logger log;

    @Inject
    OccupationService service;

    @Context
    UriInfo uriInfo;

    @GET
    @Path("/")
    @RolesAllowed({"admin"})
    public PageData<Occupation> getAll(@RestQuery("label__regex") String searchValue, @RestQuery("ids") @Separator(",") List<Long> ids) {
        log.debug("OccupationApi.getAll");
        PageRequest pageRequest;
        if (ids != null && !ids.isEmpty()) {
            pageRequest = PageRequest.newBuilder().setPage(0).setLimit(1000000).setSort("id").setOrder("asc").build();
        } else {
            pageRequest = PageUtil.getPage(uriInfo.getQueryParameters());
        }
        return service.getPageByParams(ids, searchValue, pageRequest);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"admin"})
    public Occupation getById(@PathParam("id") Long id) {
        log.debug("OccupationApi.getById");
        return service.getById(id);
    }
}