// import React, { useEffect, useState } from "react";
// import CarCrashIcon from "@mui/icons-material/CarCrash";
// function UnoccupiedParkingCard() {
//   const [unoccupiedCount, setUnoccupiedCount] = useState(0);
//   useEffect(() => {
//     const fetchUnoccupiedCount = async () => {
//       try {
//         const jwtToken = localStorage.getItem("jwt");
//         const response = await fetch("http://localhost:8082/api/getAllFlats", {
//           headers: {
//             Authorization: `Bearer ${jwtToken}`,
//             "Content-Type": "application/json",
//           },
//         });
//         if (!response.ok) throw new Error("Failed to fetch flats");
//         const data = await response.json();
//         const unoccupiedFlats = data.filter((flat) => flat.occupied === false);
//         setUnoccupiedCount(unoccupiedFlats.length);
//       } catch (error) {
//         console.error("Error fetching flat count:", error);
//       }
//     };
//     fetchUnoccupiedCount();
//   }, []);
//   return (
//     <div className="flex flex-col  p-2 space-y-3 bg-red-200 rounded-md text-red-600  h-[100px]">
//       <div className="flex flex-row justify-between items-center">
//         <div className="font-bold text-4xl">{unoccupiedCount}</div>
//         <div className="rounded-full bg-white p-0.5">
//           <CarCrashIcon />
//         </div>
//       </div>
//       <div className="text-xs text-center">No of Parking lots Unoccupied</div>
//     </div>
//   );
// }

// export default UnoccupiedParkingCard;
import React, { useEffect, useState } from "react";
import CarCrashIcon from "@mui/icons-material/CarCrash";

function UnoccupiedParkingCard() {
  const [unoccupiedCount, setUnoccupiedCount] = useState(null); // Handle loading state with `null`

  useEffect(() => {
    const fetchUnoccupiedCount = async () => {
      try {
        const jwtToken = localStorage.getItem("jwt");
        const response = await fetch("http://localhost:8082/api/getAllFlats", {
          headers: {
            Authorization: `Bearer ${jwtToken}`,
            "Content-Type": "application/json",
          },
        });
        if (!response.ok) throw new Error("Failed to fetch flats");
        const data = await response.json();
        const unoccupiedFlats = data.filter((flat) => flat.occupied === false);
        setUnoccupiedCount(unoccupiedFlats.length);
      } catch (error) {
        console.error("Error fetching flat count:", error);
        setUnoccupiedCount("Error");
      }
    };
    fetchUnoccupiedCount();
  }, []);

  return (
    <div
      className="flex flex-col p-4 space-y-3 bg-red-200 rounded-md text-red-600 h-[120px] shadow-lg transition-transform transform hover:scale-105"
      title="Unoccupied Parking Information"
    >
      <div className="flex flex-row justify-between items-center">
        <div className="font-bold text-4xl">
          {unoccupiedCount !== null
            ? unoccupiedCount !== "Error"
              ? unoccupiedCount
              : "N/A"
            : "Loading..."}
        </div>
        <div className="rounded-full bg-white p-2">
          <CarCrashIcon
            sx={{ fontSize: "2rem", color: "#e74c3c" }}
            aria-label="Unoccupied Parking Icon"
          />
        </div>
      </div>
      <div className="text-xs text-center">
        Number of Unoccupied Parking Lots
      </div>
    </div>
  );
}

export default UnoccupiedParkingCard;
