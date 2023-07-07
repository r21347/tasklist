package controllers;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //EntityManagerのインスタンスを作成
        EntityManager em = DBUtil.createEntityManager();

        //EntityManagerのメソッドcreateNamedQueryを使用して名前付きクエリを実行する
        //getResultList()を使用してListで複数の結果を取得する
        List<Task> tasks = em.createNamedQuery("getAllTasks", Task.class).getResultList();

        //データの登録件数を取得する（おまじない）
        response.getWriter().append(Integer.valueOf(tasks.size()).toString());
        
        //EntityManagerを解放
        em.close();

    }

}