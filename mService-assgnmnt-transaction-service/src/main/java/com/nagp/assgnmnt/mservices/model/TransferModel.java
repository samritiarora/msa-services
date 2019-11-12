package com.nagp.assgnmnt.mServices.model;

import java.util.UUID;

@lombok.Data
public class TransferModel
{
    private UUID from;
    private UUID to;
    private double amount;
}