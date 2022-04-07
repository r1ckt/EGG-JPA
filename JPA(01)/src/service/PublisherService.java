package service;

import entity.Publisher;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import persistence.PublisherDAO;

public class PublisherService {

    private PublisherDAO publisherDAO;
    private Scanner scan;

    public PublisherService() {
        this.publisherDAO = new PublisherDAO();
        this.scan = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);

    }

    public void createPublisher() throws Exception {
        Publisher publisher = new Publisher();
        try {
            System.out.println("Ingresar el nombre de la editorial");
            String publisherName = scan.next();
            if (publisherName == null || publisherName.trim().isEmpty()) {
                System.out.println("El nombre del autor es obligatorio");
            }
            publisher.setName(publisherName);
            publisher.setRegister(Boolean.TRUE);

            publisherDAO.insert(publisher);

        } catch (Exception e) {
            throw new Exception("No se pudo crear la editorial");
        }

    }
    
    public void showPublishers(){
            try {
            List<Publisher> publishers = publisherDAO.getPublishers();

            if (publishers.isEmpty()) {
                System.out.println("La lista de editoriales esta vacia");
            } else {
                System.out.printf("%-20s%-20s%-20s \n", 
                        "Id", "Nombre", "Active");
                for (Publisher publisher : publishers) {
                    System.out.printf("%-20s%-20s%-20s\n" ,
                            publisher.getId(), publisher.getName(), publisher.getRegister());

                }
            }
        } catch (Exception e) {
        }
    }
    
    public void disable(String name) throws Exception {
        try {
            List<Publisher> publishers = publisherDAO.getPublisherByName(name);

            if (publishers.isEmpty()) {
                System.out.println("No existen editoriales con ese nombre");
            } else {
                for (Publisher publisher : publishers) {
                    if(publisher.getName().equalsIgnoreCase(name)){
                        publisher.setRegister(Boolean.FALSE);
                        publisherDAO.update(publisher);
                        System.out.println("La editorial se dio de baja con exito!");
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("No se pudo dar de baja la editorial");
        }

    }
    public void activate(String name) throws Exception {
        try {
            List<Publisher> publishers = publisherDAO.getPublisherByName(name);

            if (publishers.isEmpty()) {
                System.out.println("No existen editoriales con ese nombre");
            } else {
                for (Publisher publisher : publishers) {
                    if(publisher.getName().equalsIgnoreCase(name)){
                        publisher.setRegister(Boolean.TRUE);
                        publisherDAO.update(publisher);
                        System.out.println("La editorial se dio de alta con exito!");
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("No se pudo dar de alta la editorial");
        }

    }
    public void modifyPublisher(String name, String newName){
        try {
            List<Publisher> publishers = publisherDAO.getPublishers();
            if(publishers.isEmpty()){
                System.out.println("La lista de editoriales estpa vacia");
            } else {
                for (Publisher publisher : publishers) {
                    if(publisher.getName().equalsIgnoreCase(name)){
                        publisher.setName(newName);
                        publisherDAO.update(publisher);
                        System.out.println("La editorial se edito con éxito!");
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
