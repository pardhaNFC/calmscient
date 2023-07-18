/*
 *
 *      Copyright (c) 2023- NFC Solutions, - All Rights Reserved
 *      All source code contained herein remains the property of NFC Solutions Incorporated
 *      and protected by trade secret or copyright law of USA.
 *      Dissemination, De-compilation, Modification and Distribution are strictly prohibited unless
 *      there is a prior written permission or license agreement from NFC Solutions.
 *
 *      Author : @Pardha Saradhi
 */

package com.calmscient.activities

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.calmscient.base.BaseActivity
import com.calmscient.viewmodels.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

class SplashScreenActivity :BaseActivity<SplashViewModel>(){
    override val viewModel: SplashViewModel by viewModels()
    override fun setupView(savedInstanceState: Bundle?) {

    }

    override fun provideLayoutId(): View {
        TODO("Not yet implemented")
    }


}