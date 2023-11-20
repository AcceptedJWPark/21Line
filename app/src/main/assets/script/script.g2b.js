// JavaScript Document
	function g2bLink(fName, gonggoNum, gonggoCha, mode)
	{
		window.open('https://www.g2b.go.kr:8081/ep'+fName+'&bidno='+gonggoNum+'&bidseq='+gonggoCha,'','status=yes,scrollbars=yes,resizable=yes,width=850,height=580');
	}

	function g2bRLink(bidno, searchType)
	{
		//�۾����Դϴ�.
		window.open('/GT/G2bLink/G2bView.htm?bidno='+bidno+'&bidSearchType='+searchType,'','status=yes,scrollbars=yes,resizable=yes,width=850,height=580');
	}

	function g2bSearch(fName)
	{
		window.open('https://www.g2b.go.kr:8101/ep'+fName,'','status=yes,scrollbars=yes,resizable=yes,width=850,height=650');
	}

	function toFile(seq) {
		location.href = 'https://www.g2b.go.kr:8081/ep/co/fileDownload.do?fileTask=NOTIFY&fileSeq='+seq;
	}

	function toFile1(seq) {
		var conURL = '/common/getG2bFile.php?popup=true&seq='+seq;
		window.open(conURL,'g2bdown','status=no,toolbar=no,menubar=no,location=no,scrollbars=no,resizable=no,width=250,height=250,left=0,top=0');
	}

	function toFile(seq){
		location.href='https://www.g2b.go.kr:8081/ep/co/fileDownload.do?fileTask=NOTIFY&fileSeq='+seq;
	}

	function viewBasicMoney(gongo_bunho,gongo_chasu,idx)
	{
		var conURL = 'https://www.g2b.go.kr:8081/ep/price/baseamt/selectBaseAmtDtlPopup.do?bidno='+gongo_bunho+'&bidseq='+gongo_chasu+'&taskClCd='+idx;
		javascript:window.open(conURL,'hiWin','status=yes,toolbar=yes,menubar=no,location=no,scrollbars=yes,resizable=yes,width=750,height=600');

	}