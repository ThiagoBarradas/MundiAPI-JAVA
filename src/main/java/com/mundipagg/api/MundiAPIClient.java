package com.mundipagg.api;

import com.mundipagg.api.controllers.*;

public class MundiAPIClient {

    private Configuration Configuration;
    private SubscriptionsController SubscriptionsController;
    private OrdersController OrdersController;
    private PlansController PlansController;
    private InvoicesController InvoicesController;
    private CustomersController CustomersController;
    private ChargesController ChargesController;
    private RecipientsController RecipientsController;
    private TokensController TokensController;
    private SellersController SellersController;
    private TransactionsController TransactionsController;
    private TransfersController TransfersController;

    public MundiAPIClient() {
        this.Configuration = new Configuration();
        this.Init();
    }

    public MundiAPIClient(String basicAuthUserName, String basicAuthPassword) {
        this.Configuration = new Configuration();
        this.Configuration.basicAuthUserName = basicAuthUserName;
        this.Configuration.basicAuthPassword = basicAuthPassword;

        this.Init();
    }

    private void Init() {
        this.SubscriptionsController = new SubscriptionsController(this.Configuration);
        this.OrdersController = new OrdersController(this.Configuration);
        this.PlansController = new PlansController(this.Configuration);
        this.InvoicesController = new InvoicesController(this.Configuration);
        this.CustomersController = new CustomersController(this.Configuration);
        this.ChargesController = new ChargesController(this.Configuration);
        this.RecipientsController = new RecipientsController(this.Configuration);
        this.TokensController = new TokensController(this.Configuration);
        this.SellersController = new SellersController(this.Configuration);
        this.TransactionsController = new TransactionsController(this.Configuration);
        this.TransfersController = new TransfersController(this.Configuration);
    }

    public SubscriptionsController getSubscriptions() {
        return this.SubscriptionsController;
    }

    public OrdersController getOrders() {
        return this.OrdersController;
    }

    public PlansController getPlans() {
        return this.PlansController;
    }

    public InvoicesController getInvoices() {
        return this.InvoicesController;
    }

    public CustomersController getCustomers() {
        return this.CustomersController;
    }

    public ChargesController getCharges() {
        return this.ChargesController;
    }

    public RecipientsController getRecipients() {
        return this.RecipientsController;
    }

    public TokensController getTokens() {
        return this.TokensController;
    }

    public SellersController getSellers() {
        return this.SellersController;
    }

    public TransactionsController getTransactions() {
        return this.TransactionsController;
    }

    public TransfersController getTransfers() {
        return this.TransfersController;
    }
}