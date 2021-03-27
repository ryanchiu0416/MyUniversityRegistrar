package com.src.client;
/*
    The Member class is a base class that represents all users in the Registrar system.
    A Member instance has 2 attributes: idNumber and cNetID.
 */
public class Member {
    protected int idNumber;
    protected String cNetID;
    public Member(int idNumber, String cNetID) {
        this.idNumber = idNumber;
        this.cNetID = cNetID;
    }
}
