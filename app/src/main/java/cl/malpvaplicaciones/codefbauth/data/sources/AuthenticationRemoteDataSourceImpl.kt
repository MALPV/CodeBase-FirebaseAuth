package cl.malpvaplicaciones.codefbauth.data.sources

import android.util.Log
import cl.malpvaplicaciones.codefbauth.domain.models.User
import cl.malpvaplicaciones.codefbauth.domain.utils.ResultState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import timber.log.Timber


class AuthenticationRemoteDataSourceImpl(
    private val firebaseAuth: FirebaseAuth
) : AuthenticationRemoteDataSource {

    override suspend fun register(email: String, password: String): ResultState<User> = try {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        Timber.i("register -> Success ${result.user?.uid}")
        ResultState.Success(
            User(
                uuid = result.user!!.uid,
                name = result.user!!.displayName ?: "",
                email = result.user!!.email ?: ""
            )
        )
    }catch (e: Exception){
        Timber.e("register -> Error $e")
        ResultState.Error(e)
    }

    override suspend fun login(email: String, password: String): ResultState<User> = try {
        val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        Timber.i("register -> Success " + result.user?.uid)
        ResultState.Success(
            User(
                uuid = result.user!!.uid,
                name = result.user!!.displayName ?: "",
                email = result.user!!.email ?: ""
            )
        )
    } catch (e: Exception) {
        Timber.e("register -> Error $e")
        ResultState.Error(e)
    }

    override suspend fun logout() = firebaseAuth.signOut()

    override fun getCurrentUser(): User? = firebaseAuth.currentUser?.let {
        User(
            uuid = it.uid,
            name = it.displayName ?: "",
            email = it.email ?: ""
        )
    }

}
