package rainbow_rider.kirin.a0606.transfer.async.user;

public class ListItem {

    private Long mId;
    private String mIconUrl;
    private String mTitle;

    public ListItem( ) {}

    public Long getId( ) {
        return this.mId;
    }

    public void setId( Long id ) {
        this.mId = ( id );
    }

    public String getIconUrl( ) {
        return this.mIconUrl;
    }

    public void setIconUrl( String iconUrl ) { this.mIconUrl = ( iconUrl ); }

    public String getTitle( ) {
        return this.mTitle;
    }

    public void setTitle( String title ) {
        this.mTitle = ( title );
    }

}
