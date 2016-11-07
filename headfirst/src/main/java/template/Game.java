package template;

/**
 * Created by spark on 11/7/16.
 */
public abstract class Game {
    public void initialize(){
        System.out.println("Game Initialized! Start playing.");
    }

    /**
     * 钩子方法
     */
    abstract void startPlay();


    public void endPlay(){
        System.out.println("Game Finished!");
    }

    //模板
    public final void play(){

        //初始化游戏
        initialize();

        //开始游戏
        startPlay();

        //结束游戏
        endPlay();
    }
}
