package com.titicorp.titi

import android.app.Application
import com.sendbird.android.exception.SendbirdException
import com.sendbird.android.handler.InitResultHandler
import com.sendbird.uikit.SendbirdUIKit
import com.sendbird.uikit.adapter.SendbirdUIKitAdapter
import com.sendbird.uikit.interfaces.UserInfo
import com.titicorp.titi.auth.UserManager


class TitiApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        UserManager.init(this)

        SendbirdUIKit.init(object : SendbirdUIKitAdapter {
            override fun getAppId(): String {
                return "119FF869-8969-4A0D-853E-6F13E58D5611"
            }

            override fun getAccessToken(): String {
                return ""
            }

            override fun getUserInfo(): UserInfo {
                return object : UserInfo {
                    override fun getUserId(): String {
                        return "user001"
                    }

                    override fun getNickname(): String? {
                        return "User 001"
                    }

                    override fun getProfileUrl(): String? {
                        return ""
                    }
                }
            }

            override fun getInitResultHandler(): InitResultHandler {
                return object : InitResultHandler {
                    override fun onMigrationStarted() {
                        // DB migration has started.
                    }

                    override fun onInitFailed(e: SendbirdException) {
                        // If DB migration fails, this method is called.
                    }

                    override fun onInitSucceed() {
                        // If DB migration is successful, this method is called and you can proceed to the next step.
                        // In the sample app, the `LiveData` class notifies you on the initialization progress
                        // And observes the `MutableLiveData<InitState> initState` value in `SplashActivity()`.
                        // If successful, the `LoginActivity` screen
                        // Or the `HomeActivity` screen will show.
                    }
                }
            }
        }, this)
    }
}