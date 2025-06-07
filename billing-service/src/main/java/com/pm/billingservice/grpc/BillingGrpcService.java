package com.pm.billingservice.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc.BillingServiceImplBase; // Import the generated class
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
// This class implements the gRPC service for billing operations.
public class BillingGrpcService extends BillingServiceImplBase{

    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);
    @Override
    public void createBillingAccount(BillingRequest billingRequest, StreamObserver<BillingResponse> responseObserver) {
        log.info("createBillingAccount called with request: {}", billingRequest);
        // Here you would typically call a service to handle the business logic or save to a database.

        BillingResponse response = BillingResponse.newBuilder().setAccountId("12345")
                .setStatus("ACTIVE")
                .build();

        // Send multiple responses if needed
        responseObserver.onNext(response);
        // Indicate that the response is complete
        responseObserver.onCompleted();
    }
}