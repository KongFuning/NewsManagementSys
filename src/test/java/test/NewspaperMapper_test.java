package test;

import com.yy.dao.NewspaperMapper;
import com.yy.dao.UserMapper;
import com.yy.pojo.Newspaper;
import com.yy.pojo.User;
import com.yy.utils.myBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewspaperMapper_test {
    private SqlSession sqlSession;
    private NewspaperMapper mapper;

    @Before
    public void init(){
        sqlSession = myBatisUtils.getSqlSessionFactory().openSession(true);
        mapper = sqlSession.getMapper(NewspaperMapper.class);
    }

    @Test
    public void getAllUser_test(){
        List<Newspaper> newspapers = mapper.getAllNewspaper();
        for (Newspaper newspaper : newspapers) {
            System.out.println(newspaper);
        }
    }

    @Test
    public void addNews_test(){
        Newspaper newspaper = new Newspaper();
        newspaper.setName("《扬子晚报》");
        newspaper.setPublisher("扬子");
        newspaper.setCycle("1周");
        newspaper.setOffer(100);
        newspaper.setContent("据中国政府网消息，最新数据显示，我国市场主体总量突破1.5亿户，" +
                "其中近10年就新增了1亿户。个体工商户数量也已突破1亿户。亿万市场主体的磅礴力量推动了我国经济总量迈上百万亿元大关、" +
                "国家财力和社会财富稳定增长，承载了7亿多人就业的基本盘，仅个体工商户就带动了近3亿人就业。");
        newspaper.setClassify_id(1);

        mapper.addNews(newspaper);
    }


    @Test
    public void updateNews_test(){
        Map map = new HashMap();
        map.put("id",5);
        map.put("name","《歪歪日报》");
        map.put("content","YY语音最早用于魔兽玩家的团队语音指挥通话，逐渐吸引了部分传奇私服用户，" +
                "最后发展为穿越火线游戏用户必备的团队语音工具，笔者曾在穿越火线游戏中目测，99%的战队收人广告后面都挂着YY语音ID。" +
                "2009年初YY娱乐用户已经形成了可以和游戏用户抗衡的用户群，YY语音的娱乐公会开始逐步超越游戏公会，人气也日渐增长。时至今日，" +
                "YY语音已经成为集合团队语音、好友聊天、视频功能、频道K歌、视频直播、YY群聊天、应用游戏、在线影视等功能为一体的综合型即时通讯软件。\n" +
                "由于YY语音的高清晰、操作方便等特点，已吸引越来越多的教育行业入驻YY，开展网络教育平台，比较著名的有外语教学频道、平面设计教学频道、心理学教育频道等等！");
        System.out.println(mapper.updateNews(map));
    }

    @Test
    public void deleteNewsById_test(){
        System.out.println(mapper.deleteNewsById(7));;
    }
    @After
    public void destroy(){
        sqlSession.close();
    }
}
