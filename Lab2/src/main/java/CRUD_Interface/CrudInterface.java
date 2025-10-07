package CRUD_Interface;

public interface CrudInterface {
    public String create();
    public String  viewAll();
    public String update(int id);
    public void delete(int id);
}
