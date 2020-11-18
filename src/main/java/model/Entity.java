package model;

/**
 * This class represents an entity in the CRM
 *
 * @author Margaux SCHNELZAUER
 *
 * @inv !this.name.isEmpty() && this.name != null && this.siret.length() == 14 && this.intern_nb >= 0 && !this.type.isEmpty() && this.type != null && this.description != null
 */
public class Entity {

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
     *
     * @pre !name.isEmpty() && name != null && siret.length() == 14 && !type.isEmpty() && type != null
     */
    public Entity(String name, String siret, String type) {
        // pre condition
        assert !name.isEmpty() && name != null && siret.length() == 14 && !type.isEmpty() && type != null : "Pre condition violated";

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
    public String getSiret() {
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
     * @pre !name.isEmpty() && name != null
     */
    public void setName(String name) {
        // pre condition
        assert !name.isEmpty() && name != null : "Pre condition violated";

        this.name = name;
        this.inv();
    }

    /**
     * Set a new entity address
     *
     * @param address : the new entity address
     *
     * @pre address != null
     */
    public void setAddress(String address) {
        // pre condition
        assert address != null : "Pre condition violated";

        this.address = address;
        this.inv();
    }

    /**
     * Set a new entity siret
     *
     * @param siret: the new entity siret
     *
     * @pre siret.length() == 14
     */
    public void setSiret(String siret) {
        // pre condition
        assert siret.length() == 14 : "Pre condition violated";

        this.siret = siret;
        this.inv();
    }

    /**
     * Set a new entity description
     *
     * @param description : the new entity description
     *
     * @pre description != null
     */
    public void setDescription(String description) {
        // pre condition
        assert description != null : "Pre condition violated";

        this.description = description;
        this.inv();
    }

    /**
     * Set a new number of intern
     *
     * @param intern_nb : the new number of intern
     *
     * @pre intern_nb >= 0
     */
    public void setIntern_nb(int intern_nb) {
        // pre condition
        assert intern_nb >= 0 : "Pre condition violated";

        this.intern_nb = intern_nb;
        this.inv();
    }

    /**
     * Set a new entity type
     *
     * @param type : the new entity type
     *
     * @pre !type.isEmpty() && type != null
     */
    public void setType(String type) {
        // pre condition
        assert !type.isEmpty() && type != null: "Pre condition violated";

        this.type = type;
        this.inv();
    }

    //-----------------------------------------------------------------//

    /**
     * The invariant of the class
     */
    private void inv(){
        assert !this.name.isEmpty() && this.name != null && this.siret.length() == 14 && this.intern_nb >= 0 && !this.type.isEmpty() && this.type != null && this.description != null: "Invariant violated";
    }
}
