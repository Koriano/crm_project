package model;

/**
 * This class represents an entity in the CRM
 *
 * @author Margaux SCHNELZAUER
 *
 * @inv !"".equals(this.name) && this.siret != 0 && this.intern_nb >= 0 && !"".equals(this.type)
 */
public class Entity {

    private String name;

    private String address;

    private int siret;

    private String description;

    private int intern_nb;

    private String type;

    /**
     * The constructor of Entity
     *
     * @param name
     * @param siret
     * @param type
     *
     * @pre !"".equals(name) && siret != 0 && !"".equals(type)
     */
    public Entity(String name, int siret, String type) {
        // pre condition
        assert !"".equals(name) && siret != 0 && !"".equals(type) : "Pre condition violated";

        this.name = name;
        this.siret = siret;
        this.type = type;
        this.address ="";
        this.description ="";
        this.intern_nb = 0;

        this.inv();
    }

    //-----------------------------------------------------------------//

    /**
     * Get the entity name
     *
     * @return the entity name
     */
    public String getName() {
        this.inv();
        return this.name;
    }

    /**
     * Get the entity address
     *
     * @return the entity address
     */
    public String getAddress() {
        this.inv();
        return this.address;
    }

    /**
     * Get the entity siret
     *
     * @return the entity siret
     */
    public int getSiret() {
        this.inv();
        return this.siret;
    }

    /**
     * Get the entity description
     *
     * @return the entity description
     */
    public String getDescription() {
        this.inv();
        return this.description;
    }

    /**
     * Get the number of intern in the entity
     *
     * @return the number of intern
     */
    public int getIntern_nb() {
        this.inv();
        return this.intern_nb;
    }

    /**
     * Get the entity type
     *
     * @return the entity type
     */
    public String getType() {
        this.inv();
        return this.type;
    }

    //-----------------------------------------------------------------//

    /**
     * Set a new name
     *
     * @param name : the new entity name
     *
     * @pre !"".equals(name)
     */
    public void setName(String name) {
        // pre condition
        assert !"".equals(name) : "Pre condition violated";

        this.name = name;
        this.inv();
    }

    /**
     * Set a new entity address
     *
     * @param address : the new entity address
     */
    public void setAddress(String address) {
        this.address = address;
        this.inv();
    }

    /**
     * Set a new entity siret
     *
     * @param siret: the new entity siret
     *
     * @pre this.siret != 0
     */
    public void setSiret(int siret) {
        // pre condition
        assert this.siret != 0 : "Pre condition violated";

        this.siret = siret;
        this.inv();
    }

    /**
     * Set a new entity description
     *
     * @param description : the new entity description
     */
    public void setDescription(String description) {
        this.description = description;
        this.inv();
    }

    /**
     * Set a new number of intern
     *
     * @param intern_nb : the new number of intern
     *
     * @pre this.intern_nb >= 0
     */
    public void setIntern_nb(int intern_nb) {
        // pre condition
        assert this.intern_nb >= 0 : "Pre condition violated";

        this.intern_nb = intern_nb;
        this.inv();
    }

    /**
     * Set a new entity type
     *
     * @param type : the new entity type
     *
     * @pre !"".equals(this.type)
     */
    public void setType(String type) {
        // pre condition
        assert !"".equals(this.type) : "Pre condition violated";

        this.type = type;
        this.inv();
    }

    //-----------------------------------------------------------------//

    /**
     * The invariant of the class
     */
    private void inv(){
        assert !"".equals(this.name) && this.siret != 0 && this.intern_nb >= 0 && !"".equals(this.type): "Invariant violated";
    }
}
