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

import java.util.List;

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



    @After
    public void destroy(){
        sqlSession.close();
    }
}
