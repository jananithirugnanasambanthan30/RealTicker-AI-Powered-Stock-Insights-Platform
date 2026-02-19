import React, { useState } from "react";

function App() {

  const [ticker,setTicker] = useState("");
  const [result,setResult] = useState("");
  const [loading,setLoading] = useState(false);

  const analyze = async () => {

    if(!ticker){
      alert("Enter ticker");
      return;
    }

    setLoading(true);
    setResult("");

    try{

      const response = await fetch(
        `http://localhost:8080/api/stocks/${ticker}/analyze`,
        { method:"POST" }
      );

      const data = await response.text();
      setResult(data);

    }catch(err){

      setResult("Cannot connect to backend");

    }

    setLoading(false);
  };

  return (
    <div style={{textAlign:"center",marginTop:"80px"}}>

      <h1>RealTicker AI</h1>

      <input
        placeholder="Enter stock symbol like AAPL"
        value={ticker}
        onChange={(e)=>setTicker(e.target.value)}
        style={{padding:"10px",width:"250px"}}
      />

      <br/><br/>

      <button onClick={analyze} style={{padding:"10px 20px"}}>
        Analyze Stock
      </button>

      <br/><br/>

      {loading && <h3>Analyzing...</h3>}

      <pre style={{
        width:"600px",
        margin:"auto",
        background:"#eee",
        padding:"20px",
        whiteSpace:"pre-wrap"
      }}>
        {result}
      </pre>

      <p style={{color:"gray"}}>
        This is AI-generated analysis and not financial advice.
      </p>

    </div>
  );
}
export default App;
