package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.servico.Servico;

public class ServicoDAO {

	private static ServicoDAO instance;
    protected EntityManager entityManager;

    public static ServicoDAO getInstance(){
      if (instance == null){
         instance = new ServicoDAO();
      }
      
      return instance;
    }

    private ServicoDAO() {
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

    public Servico getById(final long id) {
      return entityManager.find(Servico.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Servico> findAll() {
      return entityManager.createQuery("FROM " +
      Servico.class.getName()).getResultList();
    }

    public void persist(Servico servico) {
      try {
         entityManager.getTransaction().begin();
         entityManager.persist(servico);
         entityManager.getTransaction().commit();
      } catch (Exception ex) {
         ex.printStackTrace();
         entityManager.getTransaction().rollback();
      }
    }

    public void merge(Servico servico) {
      try {
         entityManager.getTransaction().begin();
         entityManager.merge(servico);
         entityManager.getTransaction().commit();
      } catch (Exception ex) {
         ex.printStackTrace();
         entityManager.getTransaction().rollback();
      }
    }

    public void remove(Servico servico) {
      try {
         entityManager.getTransaction().begin();
         servico = entityManager.find(Servico.class, servico.getId());
         entityManager.remove(servico);
         entityManager.getTransaction().commit();
      } catch (Exception ex) {
         ex.printStackTrace();
         entityManager.getTransaction().rollback();
      }
    }

    public void removeById(final Long id) {
      try {
         Servico servico = getById(id);
         remove(servico);
      } catch (Exception ex) {
         ex.printStackTrace();
      }
    }
	
}
