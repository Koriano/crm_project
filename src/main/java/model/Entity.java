package model;

/**
 * This class represents an entity in the CRM
 *
 * @author Margaux SCHNELZAUER
 */
public class Entity {

    /**
     * The entity id
     */
    private int id;

    /**
     * The entity name
     */
    private String name;

    /**
     * The entity address
     */
    private String address;

    /**
     * The entity siret number
     */
    private String siret;

    /**
     * The entity description
     */
    private String description;

    /**
     * The inter number in the entity
     */
    private int intern_nb;

    /**
     * The entity type
     */
    private String type;


    /**
     * The constructor of Entity
     *
     * @param name : the entity name
     * @param siret : the siret number
     * @param type : the entity type
     * @param id : the entity id
     *
     */
    public Entity(String name, String siret, String type, int id) {

        this.name = name;
        this.siret = siret;
        this.type = type;
        this.address ="";
        this.description ="";
        this.intern_nb = 0;
        this.id = id;
    }

    /**
     * The constructor of Entity
     *
     * @param name : the entity name
     * @param siret : the siret number
     * @param type : the entity type
     *
     */
    public Entity(String name, String siret, String type) {

        this.name = name;
        this.siret = siret;
        this.type = type;
        this.address ="";
        this.description ="";
        this.intern_nb = 0;
        this.id = 0;
    }

    //-----------------------------------------------------------------//

    /**
     * Get the entity name
     *
     * @return the entity name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the entity address
     *
     * @return the entity address
     */
    public String getAddress() {
        return this.address;
    }

    /**
     * Get the entity siret
     *
     * @return the entity siret
     */
    public String getSiret() {
        return this.siret;
    }

    /**
     * Get the entity description
     *
     * @return the entity description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Get the number of intern in the entity
     *
     * @return the number of intern
     */
    public int getIntern_nb() {
        return this.intern_nb;
    }

    /**
     * Get the entity type
     *
     * @return the entity type
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the id of the entity
     *
     * @return the entity id
     */
    public int getId() {
        return this.id;
    }

    //-----------------------------------------------------------------//

    /**
     * Set a new name
     *
     * @param name : the new entity name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set a new entity address
     *
     * @param address : the new entity address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Set a new entity siret
     *
     * @param siret: the new entity siret
     */
    public void setSiret(String siret) {
        this.siret = siret;
    }

    /**
     * Set a new entity description
     *
     * @param description : the new entity description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Set a new number of intern
     *
     * @param intern_nb : the new number of intern
     */
    public void setIntern_nb(int intern_nb) {
        this.intern_nb = intern_nb;
    }

    /**
     * Set a new entity type
     *
     * @param type : the new entity type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Set a new entity id
     *
     * @param id : the new entity id
     */
    public void setId(int id) {
        this.id = id;
    }

}
