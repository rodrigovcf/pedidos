package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.pedido.Pedido;

public class PedidoDAO {

	private static PedidoDAO instance;
    protected EntityManager entityManager;

    public static PedidoDAO getInstance(){
      if (instance == null){
         instance = new PedidoDAO();
      }
      
      return instance;
    }

    private PedidoDAO() {
      entityManager = getEntityManager();
    }

    private EntityManager getEntityManager() {
     EntityManagerFactory factory =
     Persistence.createEntityManagerFactory("persistenciaJPA");
     if (entityManager == null) {
       entityManager = factory.createEntityManager();
     }

     return entityManager;
    }

    public Pedido getById(final long id) {
      return entityManager.find(Pedido.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Pedido> findAll() {
      return entityManager.createQuery("FROM " +
      Pedido.class.getName()).getResultList();
    }

    public void persist(Pedido pedido) {
      try {
         entityManager.getTransaction().begin();
         entityManager.persist(pedido);
         entityManager.getTransaction().commit();
      } catch (Exception ex) {
         ex.printStackTrace();
         entityManager.getTransaction().rollback();
      }
    }

    public void merge(Pedido pedido) {
      try {
         entityManager.getTransaction().begin();
         entityManager.merge(pedido);
         entityManager.getTransaction().commit();
      } catch (Exception ex) {
         ex.printStackTrace();
         entityManager.getTransaction().rollback();
      }
    }

    public void remove(Pedido pedido) {
      try {
         entityManager.getTransaction().begin();
         pedido = entityManager.find(Pedido.class, pedido.getId());
         entityManager.remove(pedido);
         entityManager.getTransaction().commit();
      } catch (Exception ex) {
         ex.printStackTrace();
         entityManager.getTransaction().rollback();
      }
    }

    public void removeById(final Long id) {
      try {
         Pedido pedido = getById(id);
         remove(pedido);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
    }
	
}
