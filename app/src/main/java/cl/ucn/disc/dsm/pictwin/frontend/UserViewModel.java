package cl.ucn.disc.dsm.pictwin.frontend;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import cl.ucn.disc.dsm.pictwin.frontend.model.User;
import cl.ucn.disc.dsm.pictwin.frontend.model.Pic;
import cl.ucn.disc.dsm.pictwin.frontend.model.Twin;
import cl.ucn.disc.dsm.pictwin.frontend.service.UserRepository;


/**
 * The ViewModel of the User.
 *
 * @author Matias Canales Benavides.
 */

public final class UserViewModel extends AndroidViewModel {

    /**
     *  The Executor with two threads.
     */
    private static final Executor EXECUTOR = Executors.newFixedThreadPool(2);

    /**
     *  The Repository.
     */
    private final UserRepository userRepository = new UserRepository();

    /**
     *  The container of the User.
     */
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();

    /**
     *  The Constructor.
     *
     * @param application to use.
     */
    public UserViewModel(@NonNull Application application){

        super(application);
    }

    /**
     *  Return the UserLiveData.
     */
    public LiveData<User> getUserLiveData(){

        return this.userLiveData;
    }

    /**
     * Refresh or get the data.
     */
    public void update() {

        // Only load if we don't have data
        if (this.userLiveData.getValue() == null) {
            this.retrieveUserFromNetworkInBackground();
        }

    }

    /**
     * Test to populate the Twins.
     */
    private static Twin buildTwin(long n){
        Pic my = new Pic();
        my.setId(n);

        Pic your = new Pic();
        your.setId(n+1);

        Twin twin = new Twin();
        twin.setId(n);
        twin.setMy(my);
        twin.setYours(your);

        return twin;
    }

    /**
     * Retrieve User from API REST in the background.
     */
    private void retrieveUserFromNetworkInBackground(){
        // Run in the background.
        EXECUTOR.execute(() -> {
            // Get the User from the Repository
            Optional<User> oUser = this.userRepository.retrieveUser("admin@ucn.cl", "admin123");

            // Set the User only if exists.
            oUser.ifPresent(this.userLiveData::postValue);
        });
    }

}
