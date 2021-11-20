function writeCheck()
  {
   var form = document.writeform;
   
  if( !form.title.value ) // form에 있는 title 값이 없을 때
   {
    alert( "제목을 적어주세요" ); // 경고창 띄움
    form.title.focus(); // form 에 있는 title 위치로 이동
    return;
   }
 
  if( !form.memo.value )
   {
    alert( "내용을 적어주세요" );
    form.memo.focus();
    return;
   }
 
  form.submit();
  }

 /* 
 function query() {
      var ret = confirm("정말로 취소하시겠습니까? 입력하신 내용은 저장되지 않습니다.");
      return ret;
  } // 이거 취소 버튼 누르면 그대로임ㅠ
  */