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

package com.calmscient.di.remote.response

data class MenuStatusResponse(
    val responseCode: Int,
    val responseMessage: String
)

data class MenuItem(
    val menuId: Int,
    val menuName: String,
    val menuType: String,
    val parentId: Int,
    val isChildExist: Int,
    val seqOrder: String
)

data class MenuItemsResponse(
    val statusResponse: MenuStatusResponse,
    val menuItems: List<MenuItem>
)

