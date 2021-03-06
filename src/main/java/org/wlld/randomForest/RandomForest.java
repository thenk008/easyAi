package org.wlld.randomForest;

import org.wlld.tools.ArithUtil;

import java.util.*;

/**
 * @author lidapeng
 * @description 随机森林
 * @date 3:50 下午 2020/2/22
 */
public class RandomForest {
    private Random random = new Random();
    private Tree[] forest;
    private double trustTh = 0.1;//信任阈值

    public double getTrustTh() {
        return trustTh;
    }

    public void setTrustTh(double trustTh) {
        this.trustTh = trustTh;
    }

    public RandomForest() {
    }

    public RandomForest(int treeNub) throws Exception {
        if (treeNub > 0) {
            forest = new Tree[treeNub];
        } else {
            throw new Exception("Number of trees must be greater than 0");
        }
    }

    public void insertModel(RfModel rfModel) throws Exception {//注入模型
        if (rfModel != null) {
            Map<Integer, Node> nodeMap = rfModel.getNodeMap();
            forest = new Tree[nodeMap.size()];
            for (Map.Entry<Integer, Node> entry : nodeMap.entrySet()) {
                int key = entry.getKey();
                Tree tree = new Tree();
                forest[key] = tree;
                tree.setRootNode(entry.getValue());
            }
        } else {
            throw new Exception("model is null");
        }
    }

    public RfModel getModel() {//获取模型
        RfModel rfModel = new RfModel();
        Map<Integer, Node> nodeMap = new HashMap<>();
        for (int i = 0; i < forest.length; i++) {
            Node node = forest[i].getRootNode();
            nodeMap.put(i, node);
        }
        rfModel.setNodeMap(nodeMap);
        return rfModel;
    }

    public int forest(Object object) throws Exception {//随机森林识别
        Map<Integer, Double> map = new HashMap<>();
        for (int i = 0; i < forest.length; i++) {
            Tree tree = forest[i];
            TreeWithTrust treeWithTrust = tree.judge(object);
            int type = treeWithTrust.getType();
            //System.out.println(type);
            double trust = treeWithTrust.getTrust();
            if (map.containsKey(type)) {
                map.put(type, ArithUtil.add(map.get(type), trust));
            } else {
                map.put(type, trust);
            }
        }
        int type = 0;
        double nub = 0;
        for (Map.Entry<Integer, Double> entry : map.entrySet()) {
            double myNub = entry.getValue();
            //System.out.println("type==" + entry.getKey() + ",nub==" + myNub);
            if (myNub > nub) {
                type = entry.getKey();
                nub = myNub;
            }
        }
        if (nub < ArithUtil.mul(forest.length, trustTh)) {
            type = 0;
        }
        return type;
    }

    public void init(DataTable dataTable) throws Exception {
        //一棵树属性的数量
        if (dataTable.getSize() > 4) {
            int kNub = (int) ArithUtil.div(Math.log(dataTable.getSize()), Math.log(2));
            //int kNub = dataTable.getSize() - 1;
            // System.out.println("knNub==" + kNub);
            for (int i = 0; i < forest.length; i++) {
                Tree tree = new Tree(getRandomData(dataTable, kNub));
                forest[i] = tree;
            }
        } else {
            throw new Exception("Number of feature categories must be greater than 3");
        }
    }

    public void study() throws Exception {//学习
        for (int i = 0; i < forest.length; i++) {
            //System.out.println("开始学习==" + i + ",treeNub==" + forest.length);
            Tree tree = forest[i];
            tree.study();
        }
    }

    public void insert(Object object) {//添加学习参数
        for (int i = 0; i < forest.length; i++) {
            Tree tree = forest[i];
            tree.getDataTable().insert(object);
        }
    }

    private DataTable getRandomData(DataTable dataTable, int kNub) throws Exception {
        Set<String> attr = dataTable.getKeyType();
        Set<String> myName = new HashSet<>();
        String key = dataTable.getKey();//结果
        List<String> list = new ArrayList<>();
        for (String name : attr) {//加载主键
            if (!name.equals(key)) {
                list.add(name);
            }
        }
        for (int i = 0; i < kNub; i++) {
            int index = random.nextInt(list.size());
            myName.add(list.get(index));
            list.remove(index);
        }
        myName.add(key);
        //System.out.println(myName);
        DataTable data = new DataTable(myName);
        data.setKey(key);
        return data;
    }
}
