package com.lubanresearch.lubanmall.platformui.application.controller;

import com.lubanmall.merchantserviceapi.bean.ShopDTO;
import com.lubanmall.userserviceapi.bean.UserDTO;
import com.lubanresearch.lubanmall.common.bean.Pagination;
import com.lubanresearch.lubanmall.platformui.remote.MerchantService;
import com.lubanresearch.lubanmall.platformui.remote.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v/0.1/shops")
public class ShopController {

    //
    @Autowired
    private MerchantService merchantService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String addShop(@RequestBody ShopDTO dto) {


        UserDTO inputUser = new UserDTO();
        inputUser.setName(dto.getAccountName());
        inputUser.setMobile(dto.getMobile());
        inputUser.setPassword("123456");
        inputUser.setType((byte) 1);


        UserDTO user = userService.addUser(inputUser);


        dto.setAccountId(user.getId());
        merchantService.addShop(dto);

        return "redirect:index.html";

    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public Pagination<ShopDTO> findShops(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {


        return merchantService.findShops(page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ShopDTO getShopById(@PathVariable("id") Long id) {

        ShopDTO shopDTO =  merchantService.getShop(id);

        UserDTO userDTO = userService.getUser(shopDTO.getAccountId());

        shopDTO.setAccountName(userDTO.getName());
        shopDTO.setMobile(userDTO.getMobile());

        return shopDTO;

    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteShop(@PathVariable(value = "id") Long id) {

        merchantService.deleteShop(id);

        return "success";

    }


}
