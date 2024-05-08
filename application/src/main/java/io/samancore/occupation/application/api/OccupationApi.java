package io.samancore.occupation.application.api;

import io.samancore.occupation.application.service.OccupationService;
import io.samancore.occupation.data.utils.page.PageUtil;
import io.samancore.occupation.model.Occupation;
import io.samancore.occupation.model.PageData;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import org.jboss.logging.Logger;
import org.jboss.resteasy.reactive.RestQuery;

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
    public PageData<Occupation> getAll(@RestQuery("label__regex") String searchValue) {
        log.debug("OccupationApi.getAll");
        var pageRequest = PageUtil.getPage(uriInfo.getQueryParameters());
        return service.getPageByLabel(searchValue, pageRequest);
    }
}