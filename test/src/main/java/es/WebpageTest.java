package es;


import java.io.Serializable;

/**
 * <p>Copyright: All Rights Reserved</p>  
 * <p>Company: 北京荣之联科技股份有限公司   http://www.ronglian.com</p> 
 * <p>Description: 新闻实体 </p> 
 * <p>Author:xpguo/郭晓鹏</p>
 */
public class WebpageTest implements Serializable{
	/**
	 * 主键
	 */
	//@Id//指明这个属性映射为数据库的主键
//	@GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
//    @GeneratedValue(generator="idGenerator")
	//@Column(name="webpage_code",nullable=false)
	private String webpageCode;  //爬虫生成，唯一索引

	//@Lob
	private String content;           //正文
	
	//@Lob
	//@Column(name="no_tag_content")
	private String noTagContent;         //去标签正文
	

	public String getWebpageCode() {
		return webpageCode;
	}

	public void setWebpageCode(String webpageCode) {
		this.webpageCode = webpageCode;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNoTagContent() {
		return noTagContent;
	}

	public void setNoTagContent(String noTagContent) {
		this.noTagContent = noTagContent;
	}

	@Override
	public String toString() {
		return "WebpageTest{" +
				"webpageCode='" + webpageCode + '\'' +
				", content='" + content + '\'' +
				", noTagContent='" + noTagContent + '\'' +
				'}';
	}
}
