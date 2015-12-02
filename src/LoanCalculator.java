import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loan-calculator")
public class LoanCalculator extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String inputString = request.getParameter("loanInputs");
        // Example input: { "amount": 100000, "rate": 5.5, "months": 360 };
        // Example String: String inputString = "{ \"amount\": 100000, \"rate\": 5.5, \"months\": 360 }";
        double loanAmount = 200000;
        double annualInterestRateInPercent = 5.5;
        long loanPeriodInMonths = 360;
        try {
            JSONObject inputValues = new JSONObject(inputString);








            loanAmount = inputValues.getDouble("amount");
            System.out.print("HERE!!!!"+loanAmount);
            annualInterestRateInPercent = inputValues.getDouble("rate");
            System.out.print("HERE!!!!"+annualInterestRateInPercent);
            loanPeriodInMonths = inputValues.getLong("months");
            System.out.print("HERE!!!!"+loanPeriodInMonths);
        } catch (Exception e) {  // NullPointerException and JSONException
            // Use default values assigned before the try block
        }
        PaymentInfo info = new PaymentInfo(loanAmount, annualInterestRateInPercent, loanPeriodInMonths);
        PrintWriter out = response.getWriter();
        out.println(new JSONObject(info));
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
