package com.gestankbratwurst.bootiful;

import com.gestankbratwurst.bootiful.calculation.EvaluationContext;
import com.gestankbratwurst.bootiful.endpoint.CalculationPayload;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/calculate")
public class CalculationResource {

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response calculate(final CalculationPayload payload) {
    return new EvaluationContext(payload).produceResult().getAsDecoratedResponse();
  }

}