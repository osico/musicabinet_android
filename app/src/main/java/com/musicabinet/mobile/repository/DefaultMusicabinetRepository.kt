package com.musicabinet.mobile.repository

import com.musicabinet.mobile.api.ApiFactory
import com.musicabinet.mobile.model.login.LoginRequestBody
import com.musicabinet.mobile.model.order.execute.OrderExecuteBody
import com.musicabinet.mobile.model.order.execute.OrderExecuteParams
import com.musicabinet.mobile.model.order.execute.OrderExecuteResponse
import com.musicabinet.mobile.model.order.finish.OrderFinishExecuteBody
import com.musicabinet.mobile.model.order.finish.OrderFinishExecuteParams
import com.musicabinet.mobile.model.order.finish.OrderFinishExecuteResponse
import com.musicabinet.mobile.model.register.RegisterRequestBody
import com.musicabinet.mobile.model.register.UserInfo
import io.reactivex.Observable

/**
 * @author Kirchhoff-
 */
object DefaultMusicabinetRepository : MusicabinetRepository {

    private const val HOME_VIDEO_ID = "1eb9efe2-1428-476a-a69c-46c1cebb9dad"
    private const val HOME_NEWS_ID = "1eb9efe2-1428-476a-a69c-46c1cebb8dab"
    private const val HOME_TUTORIALS_ID = "1eb9efe2-1428-476a-a69c-46c1cebb9daa"
    private const val REQUEST_ITEM_COUNT = 5

    private const val EXECUTE_ACTION_TOKEN = "token"
    private const val EXECUTE_ACTION_CHECKOUT = "checkout"
    private const val BRAIN_TREE_ID = "54480ce1-00eb-4179-a2b6-f74daa6b9e78"

    private const val LOGIN_TYPE_EMAIL = "email"

    private const val LESSON_REQUEST_COUNT = 100

    override fun getHomeNews(start: Int) =
            ApiFactory.service.getHomeItems(HOME_NEWS_ID, true, start, REQUEST_ITEM_COUNT)

    override fun getHomeTutorial(start: Int) =
            ApiFactory.service.getHomeItems(HOME_TUTORIALS_ID, true, start, REQUEST_ITEM_COUNT)

    override fun getHomeVideo(start: Int) =
            ApiFactory.service.getHomeItems(HOME_VIDEO_ID, true, start, REQUEST_ITEM_COUNT)

    override fun getInstrumentList() =
            ApiFactory.service.getInstrumentList()

    override fun login(email: String, password: String) =
            ApiFactory.service.login(LoginRequestBody(LOGIN_TYPE_EMAIL, email, password))

    override fun getUserProfile() =
            ApiFactory.service.getUserProfile()

    override fun getInstrumentMatrix(instrumentId: String) =
            ApiFactory.service.getInstrumentMatrix(instrumentId)

    override fun getInstrumentMatrixFilter(instrumentId: String) =
            ApiFactory.service.getInstrumentMatrixFilter(instrumentId, true,
                    0, REQUEST_ITEM_COUNT)

    override fun createOrder(orderId: String) =
            ApiFactory.service.createOrder(orderId)

    override fun executeOrder(orderId: String): Observable<OrderExecuteResponse> {
        val orderExecuteParams = OrderExecuteParams(EXECUTE_ACTION_TOKEN)
        return ApiFactory.service.executeOrder(OrderExecuteBody(orderId, BRAIN_TREE_ID, orderExecuteParams))
    }

    override fun finishExecuteOrder(orderId: String, nonce: String): Observable<OrderFinishExecuteResponse> {
        val orderFinishExecuteParams = OrderFinishExecuteParams(EXECUTE_ACTION_CHECKOUT, nonce)
        return ApiFactory.service.finishExecuteOrder(OrderFinishExecuteBody(orderId, BRAIN_TREE_ID, orderFinishExecuteParams))
    }

    override fun registerUser(email: String, password: String, name: String, surname: String) =
            ApiFactory.service.registerUser(RegisterRequestBody(email, password, UserInfo(name, surname)))

    override fun getLessonGroup(id: String) =
            ApiFactory.service.getLessonGroup(id, LESSON_REQUEST_COUNT)
}