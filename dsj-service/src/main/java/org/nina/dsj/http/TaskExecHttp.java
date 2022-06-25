package org.nina.dsj.http;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import org.nina.dsj.common.dto.TaskDto;
import org.nina.dsj.common.model.JSONResult;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 描述：任务执行请求接口
 * 作者：zgc
 * 时间：2022/6/25 10:43
 */
@RetrofitClient(baseUrl = "http://127.0.0.1:9000")
public interface TaskExecHttp {

    @POST("/task/exec/delay")
    Response<JSONResult<Void>> delayTaskExec(@Body TaskDto taskDto);

    @POST("/task/exec/delay/cancel")
    Response<JSONResult<Void>> delayTaskCancel(@Body TaskDto taskDto);

}
