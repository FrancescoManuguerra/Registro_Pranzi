package Elis.College.RegistroPranzi.exception.model;

public enum Headers {
    Authorization ("Authorization"),
    contentType ("content-type"),
    TransactionID ("transactionid"),
    DateTimeMessage("datetimemessage"),
    Operation("operation"),
    Functionality("functionality"),
    ClientSystem("clientsystem"),
    ServerSystem("serversystem");

    private final String headerName;
    Headers(String headerName) {
        this.headerName = headerName;
    }

    public String headerName() { return headerName; }
    }
