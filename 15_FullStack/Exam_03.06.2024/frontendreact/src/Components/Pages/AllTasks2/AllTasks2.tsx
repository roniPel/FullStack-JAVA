import { ReactElement, JSXElementConstructor, ReactNode, ReactPortal } from "react";
import "./AllTasks2.css";
import { ColumnProps } from "../../Models/ColumnProps";
import Table  from "../../Table/Table";

type Data = {
    name: string;
    job: string;
    location: string;
  };

export function AllTasks2(): JSX.Element {
    const data = [
        {
          name: 'Cy Ganderton',
          job: 'Quality Control Specialist',
          location: 'Canada',
        },
        {
          name: 'Hart Hagerty',
          job: 'Desktop Support Technician',
          location: 'United States',
        },
        {
          name: 'Brice Swyre',
          job: 'Tax Accountant',
          location: 'China',
        },
      ];
      const columns: Array<ColumnProps<Data>> = [
        {
          key: 'name',
          title: 'Name',
        },
        {
          key: 'job',
          title: 'Job',
        },
        {
          key: 'location',
          title: 'Color',
    
          render: (_: any, record: { location: string | number | boolean | ReactElement<any, string | JSXElementConstructor<any>> | Iterable<ReactNode> | ReactPortal | null | undefined; }) => {
            return <div className="text-blue-500 font-bold">{record.location}</div>;
          },
        },
      ];
    return (
        <div className="AllTasks2">
			<div>
                <Table data={data} columns={columns} />
            </div>
        </div>
    );
}
