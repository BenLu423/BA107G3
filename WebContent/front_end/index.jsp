<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Toast</title>
</head>
<body>
<jsp:include page="/front_end/header.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-12 col-sm-1"></div>
        <div class="col-xs-12 col-sm-10 content">
            <!-- <div class="content-top"></div> -->
            <!-- Carousel -->
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>
                <!-- Wrapper for slides -->
                <div class="carousel-inner">
                    <div class="item active">
                        <img src="<%=request.getContextPath()%>/front_end/res/img/index/carousel1.jpg">
                        <!-- <img src="http://unsplash.s3.amazonaws.com/batch%209/barcelona-boardwalk.jpg" alt="First slide"> -->
                        <!-- Static Header -->
                        <div class="header-text hidden-xs">
                            <div class="col-md-12 text-center">
                                <h2>
                                    <span>　星　座　愛　情　分　析　</span>
                                </h2>
                                <br>
                                <h3>
                                    <span>覺得朋友適合這類型主題記得告訴他一起出來認識</span>
                                </h3>
                                <br>
                            </div>
                        </div><!-- /header-text -->
                    </div>
                    <div class="item">
                        <img src="<%=request.getContextPath()%>/front_end/res/img/index/carousel2.jpg">
                        <!-- <img src="http://unsplash.s3.amazonaws.com/batch%209/barcelona-boardwalk.jpg" alt="Second slide"> -->
                        <!-- Static Header -->
                        <div class="header-text hidden-xs">
                            <div class="col-md-12 text-center">
                                <h2>
                                    <span>　海　外　主　題　曲　</span>
                                </h2>
                                <br>
                                <h3>
                                    <span>曾在海外工作生活，或有留學遊學經驗的您</span>
                                </h3>
                                <br>
                            </div>
                        </div><!-- /header-text -->
                    </div>
                    <div class="item">
                        <img src="<%=request.getContextPath()%>/front_end/res/img/index/carousel3.jpg">
                        <!-- <img src="http://unsplash.s3.amazonaws.com/batch%209/barcelona-boardwalk.jpg" alt="Third slide"> -->
                        <!-- Static Header -->
                        <div class="header-text hidden-xs">
                            <div class="col-md-12 text-center">
                                <h2>
                                    <span>　料　理　狂　想　派　對　</span>
                                </h2>
                                <br>
                                <h3>
                                    <span>美食+好友，讓你一次擁有！想抓住另一半的心，就要先抓住他的胃！</span>
                                </h3>
                                <br>
                            </div>
                        </div><!-- /header-text -->
                    </div>
                </div>
                <!-- Controls -->

                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                </a>
            </div>
            <!-- /carousel -->
            
            <!-- friends -->

            <div class="col-xs-12 col-sm-6">
                <div class="tiles row">
                    <div class="col">
                        <a href="#"></a><a href="#"></a><a href="#"></a><a href="#"></a>
                        <div class="box"></div>
                    </div>
                    <div class="col">
                        <a href="#"></a><a href="#"></a><a href="#"></a><a href="#"></a>
                        <div class="box"></div>
                    </div>
                    <div class="col">
                        <a href="#"></a><a href="#"></a><a href="#"></a><a href="#"></a>
                        <div class="box"></div>
                    </div>
                    <div class="col">
                        <a href="#"></a><a href="#"></a><a href="#"></a><a href="#"></a>
                        <div class="box"></div>
                    </div>
                    <div class="col">
                        <a href="personal_page.html"></a>
                        <a href="personal_page.html"></a>
                        <a href="personal_page.html"></a>
                        <a href="personal_page.html"></a>
                        <div class="box"></div>
                    </div>
                    <div class="col">
                        <a href="#"></a><a href="#"></a><a href="#"></a><a href="#"></a>
                        <div class="box"></div>
                    </div>
                    <div class="col">
                        <a href="#"></a><a href="#"></a><a href="#"></a><a href="#"></a>
                        <div class="box"></div>
                    </div>
                    <div class="col">
                        <a href="#"></a><a href="#"></a><a href="#"></a><a href="#"></a>
                        <div class="box"></div>
                    </div>
                    <div class="col">
                        <a href="#"></a><a href="#"></a><a href="#"></a><a href="#"></a>
                        <div class="box"></div>
                    </div>
                    <div class="col">
                        <a href="#"></a><a href="#"></a><a href="#"></a><a href="#"></a>
                        <div class="box"></div>
                    </div>
                    <div class="col">
                        <a href="#"></a><a href="#"></a><a href="#"></a><a href="#"></a>
                        <div class="box"></div>
                    </div>
                    <div class="col">
                        <a href="#"></a><a href="#"></a><a href="#"></a><a href="#"></a>
                        <div class="box"></div>
                    </div>
                </div>    

            </div>
            <div class="col-xs-12 col-sm-6">
                <div class="index-friend-text row">
                    <img src="<%=request.getContextPath()%>/front_end/res/img/index/index-friend.jpg">
                </div>
            </div>
            <!-- //friends -->

            <!-- diary -->
            <div class="col-xs-12 col-sm-12">
                <div class="row">
                    <div class="col-xs-12 col-sm-6">
                        <div class="index-diary-text row">
                            <img src="<%=request.getContextPath()%>/front_end/res/img/index/index-diary.jpg">
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6">
                        <div class="row">
                            <ul class="event-list">
                                <li>
                                    <img src="res/img/index-diary-1.jpg">
                                    <div class="info">
                                        <h2 class="title">我愛西門町</h2>
                                        <p class="desc">　　其實除了看電影以外我真的不喜歡去西門町但，發現西門町有個世外桃源紅樓後方有ㄧ片酒吧可以悠閒的聚聚聊天，啜小酒我對他們的水煙還蠻感興趣的改天來去......</p>
                                    </div>
                                    <div class="social">
                                        <ul>
                                            <li class="addGittip" style="width:33%;"><a href="#addGittip"><span class="fa fa-gittip"></span></a></li>
                                            <li class="addPerson" style="width:33%;"><a href="#addPerson"><span class="fa fa-address-book-o"></span></a></li>
                                        </ul>
                                    </div>
                                </li>
                                <li>
                                    <img src="res/img/index-diary-2.jpg">
                                    <div class="info">
                                        <h2 class="title">心好累</h2>
                                        <p class="desc">　　我想要好好過日子我想將你遺忘但為何你跟小三卻要出現在我夢裡好真實好清楚的夢境心好痛好痛我已經退讓了還要我怎樣可不可以不要再來打擾我的生活，你說......</p>
                                    </div>
                                    <div class="social">
                                        <ul>
                                            <li class="addGittip" style="width:33%;"><a href="#addGittip"><span class="fa fa-gittip"></span></a></li>
                                            <li class="addPerson" style="width:33%;"><a href="#addPerson"><span class="fa fa-address-book-o"></span></a></li>
                                        </ul>
                                    </div>
                                </li>
                                <li>
                                    <img src="res/img/index-diary-3.jpg">
                                    <div class="info">
                                        <h2 class="title">這是食物</h2>
                                        <p class="desc">　　Wow~~看得出這像鏡面一樣被雕琢光滑璀璨的</p>
                                    </div>
                                    <div class="social">
                                        <ul>
                                            <li class="addGittip" style="width:33%;"><a href="#addGittip"><span class="fa fa-gittip"></span></a></li>
                                            <li class="addPerson" style="width:33%;"><a href="#addPerson"><span class="fa fa-address-book-o"></span></a></li>
                                        </ul>
                                    </div>
                                </li>
                                <li>
                                    <img src="res/img/index-diary-4.jpg">
                                    <div class="info">
                                        <h2 class="title">空虛寂寞覺得冷</h2>
                                        <p class="desc">　　空虛寂寞覺得冷作雞都會！ ... 是它說座機太苦悶，做的沒興趣，我只是安慰她而已，妳也是雞，還是隻老母雞，你有沒有曾幾何時覺得空虛寂寞，覺得冷？</p>
                                    </div>
                                    <div class="social">
                                        <ul>
                                            <li class="addGittip" style="width:33%;"><a href="#addGittip"><span class="fa fa-gittip"></span></a></li>
                                            <li class="addPerson" style="width:33%;"><a href="#addPerson"><span class="fa fa-address-book-o"></span></a></li>
                                        </ul>
                                    </div>
                                </li>
                                <li>
                                    <img src="res/img/index-diary-5.jpg">
                                    <div class="info">
                                        <h2 class="title">好消息真開心</h2>
                                        <p class="desc">　　最近與許多久未見的朋友見面，都異口同聲的說，我變瘦了， 其實，是生病了，是壓力大導致的!還好經醫生的治療， 只是，短暫的新陳代謝出問題，所幸無大礙!</p>
                                    </div>
                                    <div class="social">
                                        <ul>
                                            <li class="addGittip" style="width:33%;"><a href="#addGittip"><span class="fa fa-gittip"></span></a></li>
                                            <li class="addPerson" style="width:33%;"><a href="#addPerson"><span class="fa fa-address-book-o"></span></a></li>
                                        </ul>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                    
                </div>
            </div>
            <!-- //diary -->
            
        </div>    
        <div class="col-xs-12 col-sm-1"></div>
    </div>      
</div>
<jsp:include page="/front_end/footer.jsp"></jsp:include>
</body>
</html>