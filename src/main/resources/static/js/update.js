// (1) 회원정보 수정


function update(userId) {
    alert("되나1")

    let data = $("#profileUpdate").serialize();

    console.log(data);



    $.ajax({
        type: "put",
        url:`/api/user/${userId}`,
        data:data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType:"json"

    }).done(res => {
        console.log("update성공")
        location.href = `/user/${userId}`;

    }).fail(error => {
        console.log("update실패")

    });

    alert("되나2")

}


// function update(userId, event) {
//     console.log(event);
//
//
// }

// function update() {
//     alert("update")
//
//
// }