package br.pucpcaldas.inf.ori.projeto.core;

public class User {
    private String account;
    private String password;
    private boolean create, insert, select, update, delete;
    
    public User(String account, String password, boolean create, boolean insert, boolean select, boolean update, boolean delete)
    {
        this.account = account;
        this.password = password;
        this.create = create;
        this.insert = insert;
        this.select = select;
        this.update = update;
        this.delete = delete;
                  
    }
    
    public User(boolean create, boolean insert, boolean select, boolean update, boolean delete)
    {
        this.create = create;
        this.insert = insert;
        this.select = select;
        this.update = update;
        this.delete = delete;
                  
    } 

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCreate() {
        return create;
    }

    public void setCreate(boolean create) {
        this.create = create;
    }

    public boolean isInsert() {
        return insert;
    }

    public void setInsert(boolean insert) {
        this.insert = insert;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
    
}
