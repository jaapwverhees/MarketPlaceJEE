package com.controller;

import com.controller.DAO.ProductDAO;
import com.model.product.Category;
import com.model.product.MediaType;
import com.model.product.Product;
import com.model.product.Service;
import io.swagger.annotations.Api;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

import static javax.ws.rs.core.MediaType.*;
import static javax.ws.rs.core.Response.Status.*;
import static org.slf4j.LoggerFactory.getLogger;

@Path("products")
@Api("products")
public class ProductController {

    private final Logger errorLogger = getLogger(this.getClass());

    @Inject
    private ProductDAO dao;

    //TODO check user's credentials
    @POST
    public Response addArticle(Product product) {
        if (!product.valid()) {
            return badRequestResponse();
        }
        try {
            dao.addArticle(product);
        } catch (Exception e) {
            return exceptionResponse(e);
        }
        return succesResponse("toevoegen product succesvol");
    }

    //TODO check user's credentials
    @POST
    @Path("service")
    public Response addService(Service service) {
        if (!service.valid() || service.getPriceType() == null) {
            return badRequestResponse();
        }
        try {
            dao.addArticle(service);
        } catch (Exception e) {
            return exceptionResponse(e);
        }
        return succesResponse("toevoegen service succesvol");
    }

    @GET
    @Path("by/{name}")
    public List<Product> getProductsByName(@PathParam("name") String name) {
        return dao.getProductByName(name);
    }

    @GET
    @Path("byPrice")
    public List<Product> getProductsByPrice(@QueryParam("min") BigDecimal minimum, @QueryParam("max") BigDecimal maximum) {
        return dao.getProductByPriceRange(minimum, maximum);
    }

    @GET
    @Path("byCategory/{category}")
    public List<Product> getProductsByCategory(@PathParam("category") String description) {
        return dao.getProductByCategory(description);
    }

    @GET
    @Path("all")
    public List<Product> getAllProducts() {
        return dao.getAllProducts();
    }

    @GET
    @Path("allCategories")
    public List<Category> getAllCategories() {
        return dao.getAllCategory();
    }

    @DELETE
    public Response delete(Product product) {
        if (!product.valid()) {
            return badRequestResponse();
        }
        try {
            dao.deleteArticle(product);
        } catch (Exception e) {
            return exceptionResponse(e);
        }
        return succesResponse(product.getName() + " is verwijderd");
    }

    //TODO check user's credentials and if id number is valid;
    @PUT
    public Response put(Product product) {
        if (!product.valid()) {
            return badRequestResponse();
        }
        try {
            dao.updateProduct(product);
        } catch (Exception e) {
            return exceptionResponse(e);
        }
        return succesResponse(product.getName() + " is geupdate");
    }


    private Response succesResponse(String message) {
        return Response
                .status(OK)
                .entity(message)
                .build();
    }

    private Response exceptionResponse(Exception e) {
        return Response
                .status(INTERNAL_SERVER_ERROR)
                .entity(e.getMessage())
                .build();
    }

    private Response badRequestResponse() {
        return Response
                .status(BAD_REQUEST)
                .entity("Object was invalid")
                .build();
    }

}
