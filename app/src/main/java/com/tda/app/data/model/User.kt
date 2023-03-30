import com.tda.app.data.model.UserAddress
import java.sql.Timestamp


data class User(
    val userId: Long = 0,
    val firstname: String?,
    val lastname: String?,
    val email: String?,
    val hashPassword: String?,
    val phone: String?,
    val avatar: String?,
    val status: Boolean?,
    val createDate: Timestamp?,
    val updateDate: Timestamp?,
    val address: List<UserAddress>?,
)


