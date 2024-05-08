package io.samancore.occupation.application.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;

import org.jboss.logging.Logger;

import io.samancore.occupation.application.service.OccupationService;
import io.samancore.occupation.data.utils.page.PageUtil;
import io.samancore.occupation.model.Occupation;
import io.samancore.occupation.model.PageData;


@Path("/occupation")
public class OccupationApi {

    @Inject
    Logger log;

    @Inject
    OccupationService service;

    @Context
    UriInfo uriInfo;

    @GET
    @Path("/getAll")
    public List<Occupation> getAll() {
        log.debug("OccupationApi.getAll");
        return service.getAll();
    }

    @GET
    @Path("/search/{label}")
    public List<Occupation> searchByLabel(@PathParam("label") String label) {
        log.debugf("OccupationApi.searchByLabel %s", label);
        return service.searchByLabel(label);
    }

    @GET
    @Path("/get-page/{label}/")
    public PageData<Occupation> getPageByLabel(@PathParam("label") String label) {
        log.debugf("OccupationApi.getPageByLabel %s", label);
        var pageRequest = PageUtil.getPage(uriInfo.getQueryParameters());
        return service.getPageByLabel(label, pageRequest);
    }

    @GET
    @Path("/get-page")
    public PageData<Occupation> getPage() {
        log.debug("OccupationApi.getPageByLabel");
        var pageRequest = PageUtil.getPage(uriInfo.getQueryParameters());
        return service.getPage(pageRequest);
    }
}