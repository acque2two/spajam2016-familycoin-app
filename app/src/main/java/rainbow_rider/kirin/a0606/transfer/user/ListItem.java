package rainbow_rider.kirin.a0606.transfer.user;

public class ListItem {

    private Long mId;
    private String mIconUrl;
    private String mTitle;

    public ListItem( ) {}

    public void setId( Long id ) {
        this.mId = ( id );
    }

    public void setIconUrl( String iconUrl ) { this.mIconUrl = ( iconUrl ); }

    public void setTitle( String title ) {
        this.mTitle = ( title );
    }

    public Long getId( ) {
        return this.mId;
    }

    public String getIconUrl( ) {
        return this.mIconUrl;
    }

    public String getTitle( ) {
        return this.mTitle;
    }

}
