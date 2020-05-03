package ma.dxc.service.audit;

public enum Operation {
	INSERTE_CONTACT,
    UPDATE_CONTACT,
    DELETE_CONTACT;

    private String name;

	public String getName() {
		return name;
	}

	@Override
    public String toString() {
        return name;
    }
    

}