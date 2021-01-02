package com.party_org.demo.controller;

/**
 * @author create by 李若阳
 * @description: com.party_org.demo.controller
 * Created on 2020/4/5-10:31 下午
 */

import com.party_org.demo.dao.*;
import com.party_org.demo.entity.*;
import com.party_org.demo.service.PartyInfoService;
import com.party_org.demo.service.PorterService;
import com.party_org.demo.service.UInfoService;
import com.party_org.demo.service.Usersservice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@Controller
public class UsersController {
    public Users users;
    public Optional<PartyInfo> partyInfo;
    public Optional<UInfo> detail;
    public Optional<Integer> id;
    public FileUser myUpLoadFile=new FileUser();
    private static final Logger LOGGER = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    PartyInfoDao partyInfoDao;
    @Autowired
    PartyInfoService partyInfoService;
    @Autowired
    UInfoDao uInfoDao;
    @Autowired
    PorterDao porterDao;
    @Autowired
    PorterService porterService;
    @Autowired
    UsersDao usersDao;
    @Autowired
    Usersservice usersservice;
    @Autowired
    UInfoService uInfoService;
    @Autowired
    FileUserDao fileUserDao;
    //返回一个所有账号密码列表的api
    @RequestMapping("/getAllUsers")
    public String getallusers(Model model){
        List<Map<String,Object>> resultList =new ArrayList<Map<String,Object>>();
        for(Users users1:usersDao.findAll()){
            Map<String,Object> userlist =new HashMap<String, Object>(){
                {
                    put("username", users1.getUsername());
                    put("password", users1.getPassword());
                }
            };
            resultList.add(userlist);
            model.addAttribute("resultList",resultList);
        }
        return "";
    }
    @RequestMapping("/userlist")
    public String userlist(Model model, HttpServletRequest request, HttpServletResponse response){
            detail= uInfoDao.findById(users.getId());
            uInfoService.check(detail);
            model.addAttribute("title","欢迎"+users.getUsername());
            model.addAttribute("user",users);
            model.addAttribute("detail",detail.get());
            return "dashboard/UserList";
    }
    @RequestMapping("userlist2")
    public String userlist2(Model model, HttpServletRequest request, HttpServletResponse response){
        detail= uInfoDao.findById(users.getId());
        uInfoService.check(detail);
        model.addAttribute("title","欢迎"+users.getUsername());
        model.addAttribute("user",users);
        model.addAttribute("detail",detail.get());
        return "dashboard/UserList2";
    }
    @RequestMapping("/reset")
    public String reset(Model model){
            detail= uInfoDao.findById(users.getId());
            uInfoService.check(detail);
            model.addAttribute("title","欢迎"+users.getUsername());
            model.addAttribute("user",users);
            model.addAttribute("detail",detail.get());
            return "dashboard/reset";
    }
    @RequestMapping("/reset2")
    public String reset2(Model model){
        detail= uInfoDao.findById(users.getId());
        uInfoService.check(detail);
        model.addAttribute("title","欢迎"+users.getUsername());
        model.addAttribute("user",users);
        model.addAttribute("detail",detail.get());
        return "dashboard/reset2";
    }
    @RequestMapping("/save")
    public String save(String password,String classnumber,String major,String race,String name,String idcard,String gender){
        users.setPassword(password);
        detail=uInfoDao.findByUsername(users.getUsername());
        detail.get().setName(name);
        detail.get().setPassword(password);
        detail.get().setRace(race);
        detail.get().setClassnumber(classnumber);
        detail.get().setMajor(major);
        if (idcard!=null){ detail.get().setIdentificaionNumber(idcard); }
        if (gender!=null){ detail.get().setGender(gender);}
        uInfoService.checkUser(users,detail);
        usersDao.save(users);
        uInfoDao.save(detail.get());
        if(users.getRoles().equals("ADMIN")){return "redirect:/userlist";}
        else {return "redirect:/userlist2";}
    }
    @RequestMapping("/userslist")
    public String userslist(Model model){
        detail=uInfoDao.findById(users.getId());
        List<Map<String,Object>> infoList =new ArrayList<Map<String,Object>>();
        for(UInfo users1:uInfoDao.findAll()){
            Map<String,Object> user =new HashMap<String, Object>(){{
                put("id",users1.getId());
                put("name",users1.getName());
                put("username",users1.getUsername());
                put("password",users1.getPassword());
                put("classnumber",users1.getClassnumber());
            }};
            infoList.add(user);
            model.addAttribute("resultList",infoList);
        }
        return "dashboard/UsersList";
    }
    @RequestMapping("/authority")
    public String authority(Model model){
        List<Map<String,Object>> uinfoList =new ArrayList<Map<String,Object>>();
        for(UInfo uInfo1:uInfoDao.findAll()){
            Map<String,Object> uinfo =new HashMap<String, Object>(){{
                put("id",uInfo1.getId());
                put("username",uInfo1.getUsername());
                put("classnumber",uInfo1.getClassnumber());
                put("name",uInfo1.getName());
                put("role",uInfo1.getRoles());
            }};
            uinfoList.add(uinfo);
            model.addAttribute("uinfoList",uinfoList);
        }
        return "dashboard/Authority";
    }
    @RequestMapping("/activity")
    public String activity(String username){
        return "dashboard/Activity";
    }
    @RequestMapping("/activity2")
    public String activity2(String username){
        return "dashboard/Porter";
    }
    @RequestMapping("/porter")
    public String porter(String username){
        return "dashboard/Porter";
    }
    @RequestMapping("/readygroup")
    public String checkPut(Model model){
        List<Map<String,Object>> uinfoList =new ArrayList<Map<String,Object>>();
        for(FileUser uInfo1: fileUserDao.findAll()){
            Map<String,Object> uinfo =new HashMap<String, Object>(){{
                put("id",uInfo1.getId());
                put("username",uInfo1.getUsername());
                put("name",uInfoDao.findById(uInfo1.getUid()).get().getName());
                put("classnumber",uInfoDao.findById(uInfo1.getUid()).get().getClassnumber());
                put("major",uInfoDao.findById(uInfo1.getUid()).get().getMajor());
                put("race",uInfoDao.findById(uInfo1.getUid()).get().getRace());
                put("fileName",uInfo1.getFileName());
                put("dsc",uInfo1.getDsc());
                put("wait",uInfo1.getWait());
                put("gender",uInfoDao.findById(uInfo1.getUid()).get().getGender());
            }};
            uinfoList.add(uinfo);
            model.addAttribute("uinfoList",uinfoList);
        }
        return "dashboard/Putforward";
    }
    @RequestMapping("/putforward")
    public String readyGroup2(Model model){
        model.addAttribute("birthday",uInfoService.checkBirth(uInfoDao.findByUsername(users.getUsername()).get().getBirthday()));
        return "dashboard/ReadyGroup2";
    }
    @RequestMapping("/checkput")
    public String readyGroup(Model model){
//                FilePath f1=filePathDao.findByUsername(users.getUsername());
        List<Map<String,Object>> uinfoList =new ArrayList<Map<String,Object>>();
        for(PartyInfo uInfo1: partyInfoDao.findAllByInParty(0)){
            Map<String,Object> uinfo =new HashMap<String, Object>(){{
                put("id",uInfo1.getId());
                put("name",uInfo1.getName());
                put("classnumber",uInfo1.getClassnumber());
                put("idcard",uInfo1.getIdentificaionNumber());
                put("major",uInfo1.getMajor());
                put("race",uInfo1.getRace());
                put("file",fileUserDao.findById(uInfo1.getId()));
                put("gender",uInfo1.getGender());
            }};
            uinfoList.add(uinfo);
            model.addAttribute("uinfoList",uinfoList);
        }
        return "dashboard/ReadyGroup";
    }
    @RequestMapping("/partygroup")
    public String partyGroup(Model model){
        List<Map<String,Object>> uinfoList =new ArrayList<Map<String,Object>>();
        for(PartyInfo uInfo1: partyInfoDao.findAllByInParty(1)){
            Map<String,Object> uinfo =new HashMap<String, Object>(){{
                put("id",uInfo1.getId());
                put("name",uInfo1.getName());
                put("classnumber",uInfo1.getClassnumber());
                put("idcard",uInfo1.getIdentificaionNumber());
                put("major",uInfo1.getMajor());
                put("race",uInfo1.getRace());
                put("gender",uInfo1.getGender());
            }};
            uinfoList.add(uinfo);
            model.addAttribute("uinfoList",uinfoList);
        }
        return "dashboard/PartyGroup";
    }
    @RequestMapping("/portercontroll")
    public String proterControll(){return "dashboard/PorterControll";}
    @RequestMapping("/ROLE")
    public String role(Model model){
        List<Map<String,Object>> uinfoList =new ArrayList<Map<String,Object>>();
        for(Porter uInfo1: porterDao.findAll()){
            Map<String,Object> uinfo =new HashMap<String, Object>(){{
                put("id",uInfo1.getId());
                put("title",uInfo1.getTitle());
                put("porter",uInfo1.getPorter());
                put("createTime",uInfo1.getCreateTime());
                put("name",uInfoDao.findById(uInfo1.getUid()).get().getName());
            }};
            uinfoList.add(uinfo);
            model.addAttribute("uinfoList",uinfoList);
        }
        return "dashboard/index2";
    }
    @RequestMapping("/ADMIN")
    public String admin(Model model){
        List<Map<String,Object>> uinfoList =new ArrayList<Map<String,Object>>();
        for(Porter uInfo1: porterDao.findAll()){
            Map<String,Object> uinfo =new HashMap<String, Object>(){{
                put("id",uInfo1.getId());
                put("title",uInfo1.getTitle());
                put("porter",uInfo1.getPorter());
                put("createTime",uInfo1.getCreateTime());
                put("name",uInfoDao.findById(uInfo1.getUid()).get().getName());
            }};
            uinfoList.add(uinfo);
            model.addAttribute("uinfoList",uinfoList);
        }
        return "dashboard/index";
    }
    @RequestMapping("/index")
    public String index(String username){
        return "index";
    }
    @RequestMapping("/diff")
    public String diff(String username, String password, HttpServletRequest request){
        if(username==null){
            if(users.getRoles()!=null){
                if(users.getRoles().equals("ADMIN")) return "redirect:/ADMIN";
                else return "redirect:/ROLE";
            }
            else return "redirect:/index";
        }
        else {
            if (usersservice.checkUser(username, password)) {
                users = usersDao.findByUsername(username);
                System.out.println(users.getRoles());
                if (users.getRoles().equals("ADMIN")) {
                    return "redirect:/ADMIN";
                } else {
                    return "redirect:/ROLE";
                }
            } else {
                return "redirect:/login";
            }
        }
    }
    // 删除员工
    @RequestMapping("/super/{id}")
    public String superUser(@PathVariable("id") Integer id) {
        usersservice.superUser(id);
        return "redirect:/authority";
    }
    @RequestMapping("/lower/{id}")
    public String lowerUser(@PathVariable("id") Integer id) {
        usersservice.lowerUser(id);
        return "redirect:/authority";
    }
    @RequestMapping("/beParty/{id}")
    public String PUser(@PathVariable("id") Integer id) {
        int uid=fileUserDao.findById(id).get().getUid();
        partyInfoService.up(uid,id);
        return "redirect:/readygroup";
    }
    @RequestMapping("/te")
    public String te(){
        return "404";
    }
    @RequestMapping("/text")
    public String text(String name,String password,String classnumber,String username){
        System.out.println(name+password+classnumber+username);
        Users a = new Users();
        a.setUsername(username);
        a.setPassword(password);
        usersDao.save(a);
        UInfo b=new UInfo();
        b.setUsername(username);
        b.setPassword(password);
        uInfoDao.save(b);
        System.out.println(a.getRoles());
        return "redirect:/userslist";
    }
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        uInfoDao.deleteById(id);
        return "redirect:/userslist";
    }
    @RequestMapping("/etst")
    public String etst(){
        return "dashboard/test";
    }
    @PostMapping("/upload")
    public String upload(@RequestParam("file-input") MultipartFile file,Model model,@RequestParam("textarea-input") String dsc) {
//        FilePath fileinformation;
        if (file.isEmpty()) {
            System.out.println("上传失败，请选择文件");
            return "上传失败，请选择文件";
        }
        System.out.println("文件名!!!!："+file.getOriginalFilename());
        String fileName = file.getOriginalFilename();
        String filePath = "/root/PartyOrg/file";
        File dest = new File(filePath + fileName);
        System.out.println(dsc);
        try {
            myUpLoadFile.setFileName(fileName);
            myUpLoadFile.setPath(filePath);
            myUpLoadFile.setDsc(dsc);
            myUpLoadFile.setUsername(users.getUsername());
            myUpLoadFile.setUid(users.getId());
            fileUserDao.save(myUpLoadFile);
            file.transferTo(dest);
            LOGGER.info("上传成功");
            return "dashboard/index";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "404";
    }
    @RequestMapping("/download/{fileName}")
    public String downloadFile(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("fileName") String fileName) throws UnsupportedEncodingException {

        // 获取指定目录下的第一个文件
        File scFileDir = new File("/Users/test/");
        File TrxFiles[] = scFileDir.listFiles();
        System.out.println(TrxFiles[0]);
//        String fileName = "16号楼1、2楼男生新宿舍安排.jpg"; //下载的文件名

        // 如果文件名不为空，则进行下载
        if (fileName != null) {
            //设置文件路径
            String realPath = "/Users/test/";
            File file = new File(realPath, fileName);

            // 如果文件名存在，则进行下载
            if (file.exists()) {

                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                //response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setHeader("Content-Type", "application/octet-stream;charset=utf-8");
                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
//                    response.setHeader("Content-Disposition",
//                            "attachment;filename=" + URLEncoder.encode(fileName, ENCODING));
                    // 方法2： 设置下载的文件的名称-该方式已解决中文乱码问题，swagger,postman看到的是%...等，浏览器直接输url,OK（
                    // 把文件名按UTF-8取出并按ISO8859-1编码，保证弹出窗口中的文件名中文不乱码，中文不要太多，最多支持17个中文，因为header有150个字节限制。）
//                    response.setHeader("Content-Disposition",
//                            "attachment;filename=" + new String(outFileName.getBytes(ENCODING), "ISO8859-1"));
                    // 方法3：设置下载的文件的名称-该方式已解决中文乱码问题，postman可以，，swagger看到的是%...等，浏览器直接输url,OK
//                    response.setHeader("Content-Disposition", "attachment;filename=" + outFileName + ";filename*=utf-8''"
//                            + URLEncoder.encode(outFileName, ENCODING));

                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download successfully!");
                }
                catch (Exception e) {
                    System.out.println("Download failed!");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
    @RequestMapping("/porterCreate")
    public String porterCreate(@RequestParam("porter") String porter,@RequestParam("title") String title){
        porterService.PorterSave(users.getId(),porter,title);
        return "dashboard/PorterControll";
    }
}
